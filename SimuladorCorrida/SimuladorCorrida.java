package SimuladorCorrida;

import java.util.Random;

public class SimuladorCorrida {
    public static void main(String[] args) {
        Random random = new Random();

        int distanciaMaxima = 50;
        int carro1 = 0;
        int carro2 = 0;
        boolean corridaAtiva = true;

        System.out.println("Corrida iniciando!");

        while (corridaAtiva) {
            // Sorteia velocidades entre 1 e 3
            int vel1 = random.nextInt(3) + 1;
            int vel2 = random.nextInt(3) + 1;

            // Atualiza as distâncias
            carro1 += vel1;
            carro2 += vel2;

            // Imprime o cenário da corrida
            System.out.println("--------------------------------------------------");
            
            // Linha do carro 1
            int i = 0;
            while (i < carro1 && i < distanciaMaxima) {
                System.out.print(" ");
                i++;
            }
            System.out.println("#");

            // Linha do carro 2
            i = 0;
            while (i < carro2 && i < distanciaMaxima) {
                System.out.print(" ");
                i++;
            }
            System.out.println("#");

            System.out.println("--------------------------------------------------");

            // Verifica se alguém venceu
            if (carro1 >= distanciaMaxima || carro2 >= distanciaMaxima) {
                corridaAtiva = false;
            }
        }

        // Mostra o resultado final
        System.out.println("Resultado final:");
        if (carro1 > carro2) {
            System.out.println("O carro 1 venceu a corrida!");
        } else if (carro2 > carro1) {
            System.out.println("O carro 2 venceu a corrida!");
        } else {
            System.out.println("Empate!");
        }
    }
}