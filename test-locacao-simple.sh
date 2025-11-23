#!/bin/bash

BASE_URL="http://localhost:8080"

echo "=== TESTE CRUD DE LOCAÇÃO ==="
echo ""

# Criar dados de teste
echo "1. Criando dados de teste..."
ATOR_ID=$(curl -s -X POST $BASE_URL/ator -H "Content-Type: application/json" -d '{"nome": "Tom Hanks"}' | jq -r '.id')
echo "   Ator ID: $ATOR_ID"

DIRETOR_ID=$(curl -s -X POST $BASE_URL/diretor -H "Content-Type: application/json" -d '{"nome": "Steven Spielberg"}' | jq -r '.id')
echo "   Diretor ID: $DIRETOR_ID"

CLASSE_ID=$(curl -s -X POST $BASE_URL/classe -H "Content-Type: application/json" -d '{"nome": "Lançamento", "valor": 25.0, "prazo_devolucao": "5"}' | jq -r '.id')
echo "   Classe ID: $CLASSE_ID"

TITULO_RESPONSE=$(curl -s -X POST $BASE_URL/titulo -H "Content-Type: application/json" -d "{\"nome\": \"Forrest Gump\", \"ano\": 1994, \"sinopse\": \"História de vida\", \"categoria\": \"Drama\", \"classe\": {\"id\": $CLASSE_ID}, \"diretor\": {\"id\": $DIRETOR_ID}, \"atores\": [{\"id\": $ATOR_ID}]}")
TITULO_ID=$(echo $TITULO_RESPONSE | jq -r '.id')
echo "   Título ID: $TITULO_ID"

ITEM_RESPONSE=$(curl -s -X POST $BASE_URL/item -H "Content-Type: application/json" -d "{\"num_serie\": \"DVD-001\", \"dt_aquisicao\": \"2024-01-15\", \"tipo_item\": \"DVD\", \"titulo\": {\"id\": $TITULO_ID}}")
ITEM_ID=$(echo $ITEM_RESPONSE | jq -r '.id')
echo "   Item ID: $ITEM_ID"

SOCIO_RESPONSE=$(curl -s -X POST $BASE_URL/socio -H "Content-Type: application/json" -d '{"cpf": "12345678901", "endereco": "Rua A, 123", "tel": "11999998888", "nome": "João Silva", "dt_nascimento": "1990-05-15", "sexo": "M", "ativo": true}')
SOCIO_ID=$(echo $SOCIO_RESPONSE | jq -r '.id')
echo "   Sócio ID: $SOCIO_ID"

echo ""
echo "=== INICIANDO TESTES DE LOCAÇÃO ==="
echo ""

# TEST 1: Criar Locação
echo "TEST 1: POST /locacao - Criar nova locação"
LOCACAO=$(curl -s -X POST $BASE_URL/locacao -H "Content-Type: application/json" -d "{\"dt_locacao\": \"2024-11-20\", \"dt_devolucao_prevista\": \"2024-11-23\", \"valor_cobrado\": 25.0, \"id_item\": $ITEM_ID, \"id_cliente\": $SOCIO_ID}")
echo $LOCACAO | jq
LOCACAO_ID=$(echo $LOCACAO | jq -r '.id')
echo "✓ Locação criada com ID: $LOCACAO_ID"
echo ""

# TEST 2: Listar todas
echo "TEST 2: GET /locacao - Listar todas as locações"
curl -s -X GET $BASE_URL/locacao | jq
echo "✓ Lista recuperada"
echo ""

# TEST 3: Buscar por ID
echo "TEST 3: GET /locacao/$LOCACAO_ID - Buscar por ID"
curl -s -X GET $BASE_URL/locacao/$LOCACAO_ID | jq
echo "✓ Locação encontrada"
echo ""

# TEST 4: Finalizar com atraso (3 dias)
echo "TEST 4: PUT /locacao/$LOCACAO_ID - Finalizar COM ATRASO"
echo "   Prevista: 2024-11-23, Efetiva: 2024-11-26 → Multa esperada: R\$ 15,00"
FINALIZADA=$(curl -s -X PUT $BASE_URL/locacao/$LOCACAO_ID -H "Content-Type: application/json" -d '{"dt_devolucao_efetiva": "2024-11-26"}')
echo $FINALIZADA | jq
MULTA=$(echo $FINALIZADA | jq -r '.multa_cobrado')
echo "✓ Multa calculada: R\$ $MULTA"
echo ""

# TEST 5: Tentar finalizar novamente (deve retornar 409)
echo "TEST 5: PUT /locacao/$LOCACAO_ID - Tentar finalizar novamente (deve retornar 409 Conflict)"
ERRO=$(curl -s -w "\nHTTP:%{http_code}" -X PUT $BASE_URL/locacao/$LOCACAO_ID -H "Content-Type: application/json" -d '{"dt_devolucao_efetiva": "2024-11-26"}')
HTTP_CODE=$(echo "$ERRO" | grep "HTTP:" | cut -d':' -f2)
BODY=$(echo "$ERRO" | sed '/HTTP:/d')
echo $BODY | jq
if [ "$HTTP_CODE" = "409" ]; then
  echo "✓ Erro 409 Conflict retornado corretamente"
else
  echo "✗ FALHA: Esperado 409, recebido $HTTP_CODE"
fi
echo ""

# TEST 6: Criar outra locação SEM atraso
echo "TEST 6: Criar segunda locação SEM ATRASO"
LOCACAO2=$(curl -s -X POST $BASE_URL/locacao -H "Content-Type: application/json" -d "{\"dt_locacao\": \"2024-11-21\", \"dt_devolucao_prevista\": \"2024-11-25\", \"valor_cobrado\": 25.0, \"id_item\": $ITEM_ID, \"id_cliente\": $SOCIO_ID}")
LOCACAO2_ID=$(echo $LOCACAO2 | jq -r '.id')
echo "✓ Locação 2 criada com ID: $LOCACAO2_ID"
echo ""

# TEST 7: Finalizar sem atraso
echo "TEST 7: PUT /locacao/$LOCACAO2_ID - Finalizar SEM ATRASO"
echo "   Prevista: 2024-11-25, Efetiva: 2024-11-24 → Multa esperada: R\$ 0,00"
FINALIZADA2=$(curl -s -X PUT $BASE_URL/locacao/$LOCACAO2_ID -H "Content-Type: application/json" -d '{"dt_devolucao_efetiva": "2024-11-24"}')
echo $FINALIZADA2 | jq
MULTA2=$(echo $FINALIZADA2 | jq -r '.multa_cobrado')
echo "✓ Multa calculada: R\$ $MULTA2"
echo ""

# TEST 8: Deletar
echo "TEST 8: DELETE /locacao/$LOCACAO_ID - Deletar locação"
DELETE_RESPONSE=$(curl -s -w "HTTP:%{http_code}" -X DELETE $BASE_URL/locacao/$LOCACAO_ID)
HTTP_CODE=$(echo "$DELETE_RESPONSE" | grep "HTTP:" | cut -d':' -f2)
if [ "$HTTP_CODE" = "204" ]; then
  echo "✓ Locação deletada (HTTP 204)"
else
  echo "✗ FALHA: Esperado 204, recebido $HTTP_CODE"
fi
echo ""

# TEST 9: Buscar deletada (deve retornar 404)
echo "TEST 9: GET /locacao/$LOCACAO_ID - Buscar locação deletada (deve retornar 404)"
ERRO=$(curl -s -w "\nHTTP:%{http_code}" -X GET $BASE_URL/locacao/$LOCACAO_ID)
HTTP_CODE=$(echo "$ERRO" | grep "HTTP:" | cut -d':' -f2)
BODY=$(echo "$ERRO" | sed '/HTTP:/d')
echo $BODY | jq
if [ "$HTTP_CODE" = "404" ]; then
  echo "✓ Erro 404 Not Found retornado corretamente"
else
  echo "✗ FALHA: Esperado 404, recebido $HTTP_CODE"
fi
echo ""

# TEST 10: Lista final
echo "TEST 10: GET /locacao - Lista final (deve ter apenas 1 locação)"
LISTA=$(curl -s -X GET $BASE_URL/locacao)
echo $LISTA | jq
COUNT=$(echo $LISTA | jq 'length')
echo "✓ Total de locações: $COUNT"
echo ""

echo "=== TODOS OS TESTES CONCLUÍDOS ==="
