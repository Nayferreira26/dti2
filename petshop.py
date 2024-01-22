from datetime import datetime

def calcular_preco_total(data, qtd_pequenos, qtd_grandes):
 
    petshops = {
        "Meu Canino Feliz": {"distancia": 2, "preco_semana_pequeno": 20, "preco_semana_grande": 40, "aumento_fim_de_semana": 0.2},
        "Vai Rex": {"distancia": 1.7, "preco_semana_pequeno": 15, "preco_semana_grande": 50, "aumento_fim_de_semana_pequeno": 20, "aumento_fim_de_semana_grande": 5},
        "ChowChawgas": {"distancia": 0.8, "preco_pequeno": 30, "preco_grande": 45}
    }

    
    data_formatada = datetime.strptime(data, "%d/%m/%Y")

    fim_de_semana = data_formatada.weekday() >= 5

    melhor_petshop = None
    menor_preco_total = float('inf')

    for petshop, info in petshops.items():
        preco_pequeno = info["preco_pequeno"] if "preco_pequeno" in info else info["preco_semana_pequeno"]
        preco_grande = info["preco_grande"] if "preco_grande" in info else info["preco_semana_grande"]

        if fim_de_semana:
            preco_pequeno += preco_pequeno * info["aumento_fim_de_semana_pequeno"] / 100
            preco_grande += preco_grande * info["aumento_fim_de_semana_grande"] / 100

        preco_total = qtd_pequenos * preco_pequeno + qtd_grandes * preco_grande

        if preco_total < menor_preco_total or (preco_total == menor_preco_total and info["distancia"] < melhor_petshop["distancia"]):
            melhor_petshop = {"nome": petshop, "distancia": info["distancia"], "preco_total": preco_total}
            menor_preco_total = preco_total

    return melhor_petshop

entrada = "03/08/2018 3 5"
data, qtd_pequenos, qtd_grandes = entrada.split()[0], int(entrada.split()[1]), int(entrada.split()[2])

resultado = calcular_preco_total(data, qtd_pequenos, qtd_grandes)

print(f"Melhor petshop: {resultado['nome']}")
print(f"Preço total dos banhos: R${resultado['preco_total']:.2f}")
