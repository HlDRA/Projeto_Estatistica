import java.util.Random;

public class Velha {
    static Random random = new Random();

    public static void imprimirTabuleiro(int[][] tabuleiro){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(tabuleiro[i][j]);
            }
            System.out.println();
        }
    }
    public static void jogada (int[][] tabuleiro, int jogador){
        boolean sair = false;
        int x;
        int y;
        while (!sair) {
            x = random.nextInt(3);
            y = random.nextInt(3);
            if (tabuleiro[x][y] == 0) {
                tabuleiro[x][y] = jogador;
                sair = true;
            }
        }
    }

    public static boolean ganhouLinhas (int[][] tabuleiro, int x){
        int igual = 1;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 2; j++){
                if (tabuleiro[i][j] == x && tabuleiro[i][j] == tabuleiro[i][j+1]) igual++;
            }
            if (igual == 3) return true;
            igual = 1;
        }
        return false;
    }

    public static boolean ganhouColunas (int[][] tabuleiro, int x){
        int igual = 1;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 2; j++){
                if (tabuleiro[j][i] == x && tabuleiro[j][i] == tabuleiro[j+1][i]) igual++;
            }
            if (igual == 3) return true;
            igual = 1;
        }
        return false;
    }

    public static boolean ganhouDiagonal (int[][] tabuleiro, int x){
        if (tabuleiro[0][0] == x && tabuleiro[1][1] == x && tabuleiro[2][2] == x
        || tabuleiro[0][2] == x && tabuleiro[1][1] == x && tabuleiro[2][0] == x) return true;
        else return false;
    }


    public static boolean verificarVitoria(int[][] tabuleiro, int x){
        if (ganhouLinhas(tabuleiro, x) || ganhouColunas(tabuleiro, x) || ganhouDiagonal(tabuleiro, x)) return true;
        else return false;
    }


    public static void main(String[] args) {
        int vitoriasX = 0;
        int vitoriasO = 0;
        int empates = 0;

        //definir número de partidas que serão jogadas
        for (int partidas = 0; partidas < 10000000; partidas++) {

            //declarações iniciais
            int jogadas = 0;
            int vitorioso = 5;
            int[][] tabuleiro = new int[3][3];
            boolean vitoria = false;

            //loop onde sera executado cada jogo
            while (!vitoria) {
                jogada(tabuleiro, 1);
                if (jogadas < 4) jogada(tabuleiro, 2);
                //verificando vitoria
                if (jogadas >= 2){
                    if (verificarVitoria(tabuleiro, 1)) {vitorioso = 1; vitoria = true;}
                    else if (verificarVitoria(tabuleiro,2)) {vitorioso = 2; vitoria = true;}
                    else if (jogadas == 4){vitorioso = 3; vitoria = true;}
                }
                jogadas++;
            }
            if (vitorioso == 1) vitoriasX++;
            else if (vitorioso == 2) vitoriasO++;
            else empates++;
            //imprimirTabuleiro(tabuleiro);
            //System.out.println();
        }
        System.out.println("Vitorias de x: "+ vitoriasX);
        System.out.println("Vitorias de O: "+ vitoriasO);
        System.out.println("Empates: "+ empates);
    }
}
