#!/bin/bash

# Script de testes da API Plocadora
# Execute com: bash test-api.sh

BASE_URL="http://localhost:8080"

echo "======================================"
echo "    TESTES DA API PLOCADORA"
echo "======================================"
echo ""

# Cores para output
GREEN='\033[0;32m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Função para imprimir resultado
print_test() {
    echo -e "${BLUE}[TEST]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[✓]${NC} $1"
}

print_error() {
    echo -e "${RED}[✗]${NC} $1"
}

echo "========================================"
echo "1. TESTANDO ENDPOINTS DE ATOR"
echo "========================================"

print_test "POST /ator - Criar Leonardo DiCaprio"
curl -s -X POST $BASE_URL/ator -H "Content-Type: application/json" -d '{"nome": "Leonardo DiCaprio"}' | jq .
echo ""

print_test "POST /ator - Criar Brad Pitt"
curl -s -X POST $BASE_URL/ator -H "Content-Type: application/json" -d '{"nome": "Brad Pitt"}' | jq .
echo ""

print_test "POST /ator - Criar Tom Hardy"
curl -s -X POST $BASE_URL/ator -H "Content-Type: application/json" -d '{"nome": "Tom Hardy"}' | jq .
echo ""

print_test "GET /ator - Listar todos os atores"
curl -s -X GET $BASE_URL/ator | jq .
echo ""

print_test "GET /ator/1 - Buscar ator específico"
curl -s -X GET $BASE_URL/ator/1 | jq .
echo ""

print_test "PUT /ator/1 - Atualizar ator"
curl -s -X PUT $BASE_URL/ator/1 -H "Content-Type: application/json" -d '{"id": 1, "nome": "Leonardo Wilhelm DiCaprio"}' | jq .
echo ""

print_test "DELETE /ator/2 - Deletar Brad Pitt"
curl -s -X DELETE $BASE_URL/ator/2 -w "HTTP Status: %{http_code}\n"
echo ""

echo "========================================"
echo "2. TESTANDO ENDPOINTS DE DIRETOR"
echo "========================================"

print_test "POST /diretor - Criar Christopher Nolan"
curl -s -X POST $BASE_URL/diretor -H "Content-Type: application/json" -d '{"nome": "Christopher Nolan"}' | jq .
echo ""

print_test "POST /diretor - Criar Martin Scorsese"
curl -s -X POST $BASE_URL/diretor -H "Content-Type: application/json" -d '{"nome": "Martin Scorsese"}' | jq .
echo ""

print_test "POST /diretor - Criar Quentin Tarantino"
curl -s -X POST $BASE_URL/diretor -H "Content-Type: application/json" -d '{"nome": "Quentin Tarantino"}' | jq .
echo ""

print_test "GET /diretor - Listar todos os diretores"
curl -s -X GET $BASE_URL/diretor | jq .
echo ""

print_test "GET /diretor/1 - Buscar diretor específico"
curl -s -X GET $BASE_URL/diretor/1 | jq .
echo ""

print_test "PUT /diretor/2 - Atualizar diretor"
curl -s -X PUT $BASE_URL/diretor/2 -H "Content-Type: application/json" -d '{"id": 2, "nome": "Martin Charles Scorsese"}' | jq .
echo ""

print_test "DELETE /diretor/2 - Deletar diretor"
curl -s -X DELETE $BASE_URL/diretor/2 -w "HTTP Status: %{http_code}\n"
echo ""

echo "========================================"
echo "3. TESTANDO ENDPOINTS DE CLASSE"
echo "========================================"

print_test "POST /classe - Criar classe Lançamento"
curl -s -X POST $BASE_URL/classe -H "Content-Type: application/json" -d '{"nome": "Lançamento", "valor": 15.99, "prazo_devolucao": "3"}' | jq .
echo ""

print_test "POST /classe - Criar classe Comum"
curl -s -X POST $BASE_URL/classe -H "Content-Type: application/json" -d '{"nome": "Comum", "valor": 9.99, "prazo_devolucao": "7"}' | jq .
echo ""

print_test "POST /classe - Criar classe Clássico"
curl -s -X POST $BASE_URL/classe -H "Content-Type: application/json" -d '{"nome": "Clássico", "valor": 12.99, "prazo_devolucao": "5"}' | jq .
echo ""

print_test "GET /classe - Listar todas as classes"
curl -s -X GET $BASE_URL/classe | jq .
echo ""

print_test "GET /classe/1 - Buscar classe específica"
curl -s -X GET $BASE_URL/classe/1 | jq .
echo ""

print_test "PUT /classe/1 - Atualizar classe"
curl -s -X PUT $BASE_URL/classe/1 -H "Content-Type: application/json" -d '{"id": 1, "nome": "Lançamento Premium", "valor": 19.99, "prazo_devolucao": "2"}' | jq .
echo ""

print_test "DELETE /classe/2 - Deletar classe"
curl -s -X DELETE $BASE_URL/classe/2 -w "HTTP Status: %{http_code}\n"
echo ""

echo "========================================"
echo "4. TESTANDO ENDPOINTS DE TÍTULO"
echo "========================================"

print_test "POST /titulo - Criar Inception com relacionamentos"
curl -s -X POST $BASE_URL/titulo -H "Content-Type: application/json" -d '{
  "nome": "Inception",
  "ano": 2010,
  "sinopse": "Um ladrão que rouba segredos corporativos através do uso da tecnologia de compartilhamento de sonhos",
  "categoria": "Ficção Científica",
  "id_classe": 1,
  "id_diretor": 1,
  "id_ator": 1
}' | jq .
echo ""

print_test "POST /titulo - Criar Pulp Fiction"
curl -s -X POST $BASE_URL/titulo -H "Content-Type: application/json" -d '{
  "nome": "Pulp Fiction",
  "ano": 1994,
  "sinopse": "As vidas de dois assassinos, um boxeador, um casal de bandidos se entrelaçam",
  "categoria": "Crime",
  "id_classe": 3,
  "id_diretor": 3,
  "id_ator": 3
}' | jq .
echo ""

print_test "GET /titulo - Listar todos os títulos"
curl -s -X GET $BASE_URL/titulo | jq .
echo ""

print_test "GET /titulo/1 - Buscar título específico"
curl -s -X GET $BASE_URL/titulo/1 | jq .
echo ""

print_test "PUT /titulo/1 - Atualizar título"
curl -s -X PUT $BASE_URL/titulo/1 -H "Content-Type: application/json" -d '{
  "id": 1,
  "nome": "Inception: The Dream",
  "ano": "2010",
  "sinopse": "Um ladrão que rouba segredos corporativos através do uso da tecnologia de compartilhamento de sonhos - Edição Especial",
  "categoria": "Ficção Científica",
  "classe": {"id": 1},
  "diretor": {"id": 1},
  "atores": [{"id": 1}]
}' | jq .
echo ""

print_test "DELETE /titulo/2 - Deletar título"
curl -s -X DELETE $BASE_URL/titulo/2 -w "HTTP Status: %{http_code}\n"
echo ""

echo "========================================"
echo "5. TESTANDO ENDPOINTS DE ITEM"
echo "========================================"

print_test "POST /item - Criar item DVD"
curl -s -X POST $BASE_URL/item -H "Content-Type: application/json" -d '{
  "num_serie": 123456,
  "dt_aquisicao": "2024-01-15",
  "tipo_item": "DVD",
  "titulo_id": 1
}' | jq .
echo ""

print_test "POST /item - Criar item BLUERAY"
curl -s -X POST $BASE_URL/item -H "Content-Type: application/json" -d '{
  "num_serie": 789012,
  "dt_aquisicao": "2024-02-20",
  "tipo_item": "BLUERAY",
  "titulo_id": 1
}' | jq .
echo ""

print_test "POST /item - Criar item FITA"
curl -s -X POST $BASE_URL/item -H "Content-Type: application/json" -d '{
  "num_serie": 345678,
  "dt_aquisicao": "2020-05-10",
  "tipo_item": "FITA",
  "titulo_id": 1
}' | jq .
echo ""

print_test "GET /item - Listar todos os itens"
curl -s -X GET $BASE_URL/item | jq .
echo ""

print_test "GET /item/1 - Buscar item específico"
curl -s -X GET $BASE_URL/item/1 | jq .
echo ""

print_test "PUT /item/1 - Atualizar item"
curl -s -X PUT $BASE_URL/item/1 -H "Content-Type: application/json" -d '{
  "id": 1,
  "num_serie": "999999",
  "dt_aquisicao": "2024-03-01",
  "tipo_item": "DVD"
}' | jq .
echo ""

print_test "DELETE /item/3 - Deletar item"
curl -s -X DELETE $BASE_URL/item/3 -w "HTTP Status: %{http_code}\n"
echo ""

echo "========================================"
echo "6. TESTANDO CASOS DE ERRO"
echo "========================================"

print_test "GET /ator/999 - Buscar ator inexistente (esperado 404)"
STATUS=$(curl -s -o /dev/null -w "%{http_code}" -X GET $BASE_URL/ator/999)
if [ "$STATUS" == "404" ]; then
    print_success "Retornou 404 corretamente"
else
    print_error "Esperado 404, recebido $STATUS"
fi
echo ""

print_test "POST /ator sem dados - Dados inválidos (esperado 400)"
STATUS=$(curl -s -o /dev/null -w "%{http_code}" -X POST $BASE_URL/ator -H "Content-Type: application/json" -d '{}')
if [ "$STATUS" == "400" ]; then
    print_success "Retornou 400 corretamente"
else
    print_error "Esperado 400, recebido $STATUS"
fi
echo ""

print_test "DELETE /ator/2 - Deletar recurso já deletado (esperado 404)"
STATUS=$(curl -s -o /dev/null -w "%{http_code}" -X DELETE $BASE_URL/ator/2)
if [ "$STATUS" == "404" ]; then
    print_success "Retornou 404 corretamente"
else
    print_error "Esperado 404, recebido $STATUS"
fi
echo ""

print_test "POST /titulo - Relacionamento inexistente (esperado 400)"
STATUS=$(curl -s -o /dev/null -w "%{http_code}" -X POST $BASE_URL/titulo -H "Content-Type: application/json" -d '{
  "nome": "Teste",
  "ano": 2024,
  "sinopse": "Teste",
  "categoria": "Teste",
  "id_classe": 999,
  "id_diretor": 1,
  "id_ator": 1
}')
if [ "$STATUS" == "400" ]; then
    print_success "Retornou 400 corretamente"
else
    print_error "Esperado 400, recebido $STATUS"
fi
echo ""

print_test "POST /item - Tipo inválido (esperado 400)"
STATUS=$(curl -s -o /dev/null -w "%{http_code}" -X POST $BASE_URL/item -H "Content-Type: application/json" -d '{
  "num_serie": 111111,
  "dt_aquisicao": "2024-01-01",
  "tipo_item": "INVALID",
  "titulo_id": 1
}')
if [ "$STATUS" == "400" ]; then
    print_success "Retornou 400 corretamente"
else
    print_error "Esperado 400, recebido $STATUS"
fi
echo ""

echo "========================================"
echo "     TESTES CONCLUÍDOS!"
echo "========================================"
