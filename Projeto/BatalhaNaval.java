import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {

    static Random random = new Random();

    static Scanner sc = new Scanner(System.in);

    //metodo que define as posições dos barcos no tabuleiro do jogador especificado
    public static void definirPosicoesBarcos(int[][] tabuleiro, int jogador){
        boolean sair = false;
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        tabuleiro[x][y] = jogador;
        int direcao;
        while (!sair){
            x = random.nextInt(6);
            y = random.nextInt(6);
            direcao = random.nextInt(2);
            if (direcao == 0 && y+ 1 < 6){
                if (tabuleiro[x][y] == 0 && tabuleiro[x][y + 1] == 0){
                    tabuleiro[x][y] = jogador;
                    tabuleiro[x][y + 1] = jogador;
                    sair = true;
                }
            }
            else if (direcao == 1 && x+ 1 < 6) {
                if (tabuleiro[x][y] == 0 && tabuleiro[x+ 1][y] == 0){
                    tabuleiro[x][y] = jogador;
                    tabuleiro[x+ 1][y] = jogador;
                    sair = true;
                }
            }
        }
        sair = false;
        while (!sair){
            x = random.nextInt(6);
            y = random.nextInt(6);
            direcao = random.nextInt(2);
            if (direcao == 0 && y + 2 < 6){
                if (tabuleiro[x][y] == 0 && tabuleiro[x][y + 1] == 0 && tabuleiro[x][y + 2] == 0){
                    tabuleiro[x][y] = jogador;
                    tabuleiro[x][y + 1] = jogador;
                    tabuleiro[x][y + 2] = jogador;
                    sair = true;
                }
            }
            else if (direcao == 1 && x + 2 < 6) {
                if (tabuleiro[x][y] == 0 && tabuleiro[x+ 1][y] == 0 && tabuleiro[x + 2][y] == 0){
                    tabuleiro[x][y] = jogador;
                    tabuleiro[x+ 1][y] = jogador;
                    tabuleiro[x+ 2][y] = jogador;
                    sair = true;
                }
            }
        }
    }

    //metodo para deixar o tabuleiro completamente vazio para que o jogo possa ser iniciado novamente
    public static void resetTabuleiro(int[][] tabuleiro, int[][] tabuleiro2){
        for (int x = 0; x < 6;x++){
            for (int y = 0; y < 6; y++){
                tabuleiro[x][y] = 0;
                tabuleiro2[x][y] = 0;
            }
        }
    }

    //metodo em que é a jogada é feita no tabuleiro especificado de forma aleatoria
    public static void jogada (int[][] tabuleiro){
        int x;
        int y;
        boolean sair = false;
        while (!sair){
            x = random.nextInt(6);
            y = random.nextInt(6);

            if (tabuleiro[x][y] != 9) {
                tabuleiro[x][y] = 9;
                sair = true;
            }
        }
    }

    //metodo com igual ao anterior porem com verificações necessarias para a jogada com estrategia
    public static int[] jogadaEst (int[][] tabuleiro){
        int x;
        int y;
        int[] resultado = new int[3];
        boolean sair = false;
        while (!sair){
            x = random.nextInt(6);
            y = random.nextInt(6);

            if (tabuleiro[x][y] == 2) {
                tabuleiro[x][y] = 9;
                resultado[0] = 1;
                resultado[1] = x;
                resultado[2] = y;
                return resultado;
            }
            else if (tabuleiro[x][y] != 9) {
                tabuleiro[x][y] = 9;
                return resultado;
            }
        }
        return resultado;
    }

    //metodo onde uma estrategia é aplicada sobre a jogada com o objetivo de aumentar a taxa de vitoria de um jogador especifico
    public static int[] jogadaComEstrategia(int[][] tabuleiro, int x, int y){
        boolean sempre = true;
        int[] resultado = new int[3];
        if ((x+ 1 < 6 && tabuleiro[x+1][y] !=9) || (x -1 >= 0 && tabuleiro[x-1][y] != 9)
                || (y + 1 < 6 && tabuleiro[x][y+1] != 9) || (y - 1 >= 0 && tabuleiro[x][y-1] != 9)) {
            while (sempre) {
                int estrategia = random.nextInt(4) + 1;
                if (estrategia == 1 && x + 1 < 6 && tabuleiro[x + 1][y] != 9) {
                    if (tabuleiro[x + 1][y] == 2) {
                        resultado[0] = 1;
                        resultado[1] = x + 1;
                        resultado[2] = y;
                        tabuleiro[x+1][y] = 9;
                        return resultado;
                    }
                    tabuleiro[x+1][y] = 9;
                    resultado[0] = 3;
                    return resultado;
                } else if (estrategia == 2 && x - 1 >= 0 && tabuleiro[x - 1][y] != 9) {
                    if (tabuleiro[x - 1][y] == 2) {
                        resultado[0] = 1;
                        resultado[1] = x - 1;
                        resultado[2] = y;
                        tabuleiro[x-1][y] = 9;
                        return resultado;
                    }
                    tabuleiro[x-1][y] = 9;
                    resultado[0] = 3;
                    return resultado;
                } else if (estrategia == 3 && y + 1 < 6 && tabuleiro[x][y + 1] != 9) {
                    if (tabuleiro[x][y + 1] == 2) {
                        resultado[0] = 1;
                        resultado[1] = x;
                        resultado[2] = y + 1;
                        tabuleiro[x][y+1] = 9;
                        return resultado;
                    }
                    tabuleiro[x][y+1] = 9;
                    resultado[0] = 3;
                    return resultado;
                } else if (estrategia == 4 && y - 1 >= 0 && tabuleiro[x][y - 1] != 9) {
                    if (tabuleiro[x][y - 1] == 2) {
                        resultado[0] = 1;
                        resultado[1] = x;
                        resultado[2] = y - 1;
                        tabuleiro[x][y-1] = 9;
                        return resultado;
                    }
                    tabuleiro[x][y-1] = 9;
                    resultado[0] = 3;
                    return resultado;
                }
            }
        }
        return resultado;
    }



    public static void imprimirTabuleiro(int[][] tabuleiro){
        for (int x = 0; x < 6;x++){
            for (int y = 0; y < 6; y++){
                System.out.print(tabuleiro[x][y]);
            }
            System.out.println();
        }
    }

    //verifica se o jogador especificado tem algum barco restando no seu tabuleiro
    public static boolean verificarDerrota(int[][] tabuleiro, int jogador){
        for (int x = 0; x < 6; x++){
            for (int y = 0; y < 6; y++){
                if (tabuleiro[x][y] == jogador) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int tabuleiroAzul[][] = new int[6][6];
        int tabuleiroVermelho[][] = new int[6][6];
        int vitoriasVermelho = 0;
        int vitoriasAzul = 0;
        int vitorias = 0;
        boolean parou = false;

        definirPosicoesBarcos(tabuleiroAzul,1);
        definirPosicoesBarcos(tabuleiroVermelho, 2);

        int[] est = new int[3];

        //laço onde serão executados varios jogos repetidamentes para verificar que jogador obtém a maior quantidade de vitorias
        while (vitorias < 100000) {
            if (est[0] == 1) {
                est = jogadaComEstrategia(tabuleiroVermelho,est[1],est[2]);
                if (est[0] == 1) parou = true;
                else if (est[0] == 3) parou = true;
            }
            if (!parou){
                est = jogadaEst(tabuleiroVermelho);
            }
            parou = false;
            jogada(tabuleiroAzul);

            if (verificarDerrota(tabuleiroAzul,1) || verificarDerrota(tabuleiroVermelho,2)) {

                if (verificarDerrota(tabuleiroVermelho,2)) vitoriasAzul++;
                else if (verificarDerrota(tabuleiroAzul,1)) vitoriasVermelho++;

                vitorias++;
                resetTabuleiro(tabuleiroAzul,tabuleiroVermelho);
                definirPosicoesBarcos(tabuleiroAzul,1);
                definirPosicoesBarcos(tabuleiroVermelho,2);
            }
        }
        System.out.println("vitorias azul: "+ vitoriasAzul);
        System.out.println("vitorias vermelho: " + vitoriasVermelho);

    }
}
