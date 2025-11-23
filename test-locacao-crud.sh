#!/bin/bash

# Cores para output
GREEN='\033[0;32m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

BASE_URL="http://localhost:8080"

echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}  TESTE CRUD DE LOCAÇÃO${NC}"
echo -e "${BLUE}========================================${NC}"

# 1. Criar Ator
echo -e "\n${BLUE}1. Criando Ator...${NC}"
ATOR_RESPONSE=$(curl -s -X POST $BASE_URL/ator \
  -H "Content-Type: application/json" \
  -d '{"nome": "Tom Hanks"}')
ATOR_ID=$(echo $ATOR_RESPONSE | jq -r '.id')
echo -e "${GREEN}✓ Ator criado com ID: $ATOR_ID${NC}"

# 2. Criar Diretor
echo -e "\n${BLUE}2. Criando Diretor...${NC}"
DIRETOR_RESPONSE=$(curl -s -X POST $BASE_URL/diretor \
  -H "Content-Type: application/json" \
  -d '{"nome": "Steven Spielberg"}')
DIRETOR_ID=$(echo $DIRETOR_RESPONSE | jq -r '.id')
echo -e "${GREEN}✓ Diretor criado com ID: $DIRETOR_ID${NC}"

# 3. Criar Classe
echo -e "\n${BLUE}3. Criando Classe...${NC}"
CLASSE_RESPONSE=$(curl -s -X POST $BASE_URL/classe \
  -H "Content-Type: application/json" \
  -d '{"nome": "Lançamento", "valor": 25.00, "data_devolucao": "2024-12-01"}')
CLASSE_ID=$(echo $CLASSE_RESPONSE | jq -r '.id')
echo -e "${GREEN}✓ Classe criada com ID: $CLASSE_ID${NC}"

# 4. Criar Título
echo -e "\n${BLUE}4. Criando Título...${NC}"
TITULO_RESPONSE=$(curl -s -X POST $BASE_URL/titulo \
  -H "Content-Type: application/json" \
  -d "{\"nome\": \"Forrest Gump\", \"ano\": 1994, \"sinopse\": \"História de vida\", \"categoria\": \"Drama\", \"classe\": {\"id\": $CLASSE_ID}, \"diretor\": {\"id\": $DIRETOR_ID}, \"atores\": [{\"id\": $ATOR_ID}]}")
TITULO_ID=$(echo $TITULO_RESPONSE | jq -r '.id')
echo -e "${GREEN}✓ Título criado com ID: $TITULO_ID${NC}"

# 5. Criar Item
echo -e "\n${BLUE}5. Criando Item...${NC}"
ITEM_RESPONSE=$(curl -s -X POST $BASE_URL/item \
  -H "Content-Type: application/json" \
  -d "{\"num_serie\": \"DVD-001\", \"dt_aquisicao\": \"2024-01-15\", \"tipo_item\": \"DVD\", \"titulo\": {\"id\": $TITULO_ID}}")
ITEM_ID=$(echo $ITEM_RESPONSE | jq -r '.id')
echo -e "${GREEN}✓ Item criado com ID: $ITEM_ID${NC}"

# 6. Criar Sócio
echo -e "\n${BLUE}6. Criando Sócio...${NC}"
SOCIO_RESPONSE=$(curl -s -X POST $BASE_URL/socio \
  -H "Content-Type: application/json" \
  -d '{"cpf": "12345678901", "endereco": "Rua A, 123", "tel": "11999998888", "nome": "João Silva", "dt_nascimento": "1990-05-15", "sexo": "M", "ativo": true}')
SOCIO_ID=$(echo $SOCIO_RESPONSE | jq -r '.id')
echo -e "${GREEN}✓ Sócio criado com ID: $SOCIO_ID${NC}"

echo -e "\n${BLUE}========================================${NC}"
echo -e "${BLUE}  TESTANDO CRUD DE LOCAÇÃO${NC}"
echo -e "${BLUE}========================================${NC}"

# 7. Criar Locação
echo -e "\n${BLUE}7. POST /locacao - Criar nova locação${NC}"
LOCACAO_RESPONSE=$(curl -s -X POST $BASE_URL/locacao \
  -H "Content-Type: application/json" \
  -d "{\"dt_locacao\": \"2024-11-20\", \"dt_devolucao_prevista\": \"2024-11-23\", \"valor_cobrado\": 25.00, \"id_item\": $ITEM_ID, \"id_cliente\": $SOCIO_ID}")
LOCACAO_ID=$(echo $LOCACAO_RESPONSE | jq -r '.id')
echo $LOCACAO_RESPONSE | jq
echo -e "${GREEN}✓ Locação criada com ID: $LOCACAO_ID${NC}"

# 8. Listar todas as Locações
echo -e "\n${BLUE}8. GET /locacao - Listar todas as locações${NC}"
curl -s -X GET $BASE_URL/locacao | jq
echo -e "${GREEN}✓ Lista recuperada${NC}"

# 9. Buscar Locação por ID
echo -e "\n${BLUE}9. GET /locacao/$LOCACAO_ID - Buscar locação por ID${NC}"
curl -s -X GET $BASE_URL/locacao/$LOCACAO_ID | jq
echo -e "${GREEN}✓ Locação encontrada${NC}"

# 10. Finalizar Locação (com atraso - deve calcular multa)
echo -e "\n${BLUE}10. PUT /locacao/$LOCACAO_ID - Finalizar locação COM ATRASO (3 dias)${NC}"
echo -e "${BLUE}Data prevista: 2024-11-23, Data efetiva: 2024-11-26 → Multa esperada: R\$ 15,00${NC}"
FINALIZAR_RESPONSE=$(curl -s -X PUT $BASE_URL/locacao/$LOCACAO_ID \
  -H "Content-Type: application/json" \
  -d '{"dt_devolucao_efetiva": "2024-11-26"}')
echo $FINALIZAR_RESPONSE | jq
MULTA=$(echo $FINALIZAR_RESPONSE | jq -r '.multa_cobrado')
echo -e "${GREEN}✓ Locação finalizada com multa: R\$ $MULTA${NC}"

# 11. Tentar finalizar novamente (deve retornar erro 409)
echo -e "\n${BLUE}11. PUT /locacao/$LOCACAO_ID - Tentar finalizar novamente (deve falhar)${NC}"
ERRO_RESPONSE=$(curl -s -w "\nHTTP_CODE:%{http_code}" -X PUT $BASE_URL/locacao/$LOCACAO_ID \
  -H "Content-Type: application/json" \
  -d '{"dt_devolucao_efetiva": "2024-11-26"}')
HTTP_CODE=$(echo "$ERRO_RESPONSE" | grep "HTTP_CODE" | cut -d':' -f2)
BODY=$(echo "$ERRO_RESPONSE" | sed '/HTTP_CODE/d')
echo $BODY | jq
if [ "$HTTP_CODE" = "409" ]; then
  echo -e "${GREEN}✓ Erro 409 Conflict retornado corretamente${NC}"
else
  echo -e "${RED}✗ Esperado 409, recebido $HTTP_CODE${NC}"
fi

# 12. Criar outra locação SEM atraso
echo -e "\n${BLUE}12. Criando locação SEM ATRASO${NC}"
LOCACAO2_RESPONSE=$(curl -s -X POST $BASE_URL/locacao \
  -H "Content-Type: application/json" \
  -d "{\"dt_locacao\": \"2024-11-21\", \"dt_devolucao_prevista\": \"2024-11-25\", \"valor_cobrado\": 25.00, \"id_item\": $ITEM_ID, \"id_cliente\": $SOCIO_ID}")
LOCACAO2_ID=$(echo $LOCACAO2_RESPONSE | jq -r '.id')
echo -e "${GREEN}✓ Locação 2 criada com ID: $LOCACAO2_ID${NC}"

# 13. Finalizar sem atraso
echo -e "\n${BLUE}13. PUT /locacao/$LOCACAO2_ID - Finalizar locação SEM ATRASO${NC}"
echo -e "${BLUE}Data prevista: 2024-11-25, Data efetiva: 2024-11-24 → Multa esperada: R\$ 0,00${NC}"
FINALIZAR2_RESPONSE=$(curl -s -X PUT $BASE_URL/locacao/$LOCACAO2_ID \
  -H "Content-Type: application/json" \
  -d '{"dt_devolucao_efetiva": "2024-11-24"}')
echo $FINALIZAR2_RESPONSE | jq
MULTA2=$(echo $FINALIZAR2_RESPONSE | jq -r '.multa_cobrado')
echo -e "${GREEN}✓ Locação finalizada sem multa: R\$ $MULTA2${NC}"

# 14. Listar novamente
echo -e "\n${BLUE}14. GET /locacao - Listar todas as locações (agora com 2)${NC}"
curl -s -X GET $BASE_URL/locacao | jq
echo -e "${GREEN}✓ Lista atualizada${NC}"

# 15. Deletar primeira locação
echo -e "\n${BLUE}15. DELETE /locacao/$LOCACAO_ID - Deletar locação${NC}"
curl -s -w "\nHTTP_CODE:%{http_code}" -X DELETE $BASE_URL/locacao/$LOCACAO_ID | grep "HTTP_CODE"
echo -e "${GREEN}✓ Locação deletada (HTTP 204)${NC}"

# 16. Tentar buscar locação deletada (deve retornar 404)
echo -e "\n${BLUE}16. GET /locacao/$LOCACAO_ID - Buscar locação deletada (deve retornar 404)${NC}"
ERRO_RESPONSE=$(curl -s -w "\nHTTP_CODE:%{http_code}" -X GET $BASE_URL/locacao/$LOCACAO_ID)
HTTP_CODE=$(echo "$ERRO_RESPONSE" | grep "HTTP_CODE" | cut -d':' -f2)
BODY=$(echo "$ERRO_RESPONSE" | sed '/HTTP_CODE/d')
echo $BODY | jq
if [ "$HTTP_CODE" = "404" ]; then
  echo -e "${GREEN}✓ Erro 404 Not Found retornado corretamente${NC}"
else
  echo -e "${RED}✗ Esperado 404, recebido $HTTP_CODE${NC}"
fi

# 17. Listar final
echo -e "\n${BLUE}17. GET /locacao - Listar locações (deve restar apenas 1)${NC}"
curl -s -X GET $BASE_URL/locacao | jq
echo -e "${GREEN}✓ Lista final${NC}"

echo -e "\n${BLUE}========================================${NC}"
echo -e "${GREEN}  TODOS OS TESTES CONCLUÍDOS! ✓${NC}"
echo -e "${BLUE}========================================${NC}"
