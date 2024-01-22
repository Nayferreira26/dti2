
package Projeto_Dti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class petshop {

    public static void main(String[] args) {
    	
        String entrada = "03/08/2018 3 5";
        String[] partesEntrada = entrada.split(" ");
        Date data;
        int qtdPequenos, qtdGrandes;

        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(partesEntrada[0]);
            qtdPequenos = Integer.parseInt(partesEntrada[1]);
            qtdGrandes = Integer.parseInt(partesEntrada[2]);
        } catch (ParseException e) {
            System.out.println("Erro ao processar a entrada.");
            return;
        }

        double precoTotalMeuCaninoFeliz = calcularPrecoTotal(2.0, data, qtdPequenos, qtdGrandes, 20.0, 40.0, 0.2);
        double precoTotalVaiRex = calcularPrecoTotal(1.7, data, qtdPequenos, qtdGrandes, 15.0, 50.0, 0.2);
        double precoTotalChowChawgas = calcularPrecoTotal(0.8, data, qtdPequenos, qtdGrandes, 30.0, 45.0, 0.0);

        String melhorPetshop = "";
        double menorPrecoTotal = Double.MAX_VALUE;

        if (precoTotalMeuCaninoFeliz < menorPrecoTotal) {
            melhorPetshop = "Meu Canino Feliz";
            menorPrecoTotal = precoTotalMeuCaninoFeliz;
        }

        if (precoTotalVaiRex < menorPrecoTotal) {
            melhorPetshop = "Vai Rex";
            menorPrecoTotal = precoTotalVaiRex;
        }

        if (precoTotalChowChawgas < menorPrecoTotal) {
            melhorPetshop = "ChowChawgas";
        }

        System.out.println("Melhor petshop: " + melhorPetshop);
        System.out.println("Preço total dos banhos: R$" + menorPrecoTotal);
    }

    private static double calcularPrecoTotal(double distancia, Date data, int qtdPequenos, int qtdGrandes,
            double precoPequeno, double precoGrande, double aumentoFimDeSemana) {
        boolean fimDeSemana = Calendar.DAY_OF_WEEK == 0 || Calendar.DAY_OF_WEEK == 6;
        double precoFinalPequeno = fimDeSemana ? precoPequeno * (1 + aumentoFimDeSemana) : precoPequeno;
        double precoFinalGrande = fimDeSemana ? precoGrande * (1 + aumentoFimDeSemana) : precoGrande;

        return qtdPequenos * precoFinalPequeno + qtdGrandes * precoFinalGrande;
    }
}


