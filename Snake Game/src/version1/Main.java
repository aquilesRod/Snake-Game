package version1;
import java.util.Scanner;

class Main {
  public static boolean verificaMovimento(int tabuleiro[][], int inicialX, int inicialY, int finalX, int finalY) {

    if (finalX < tabuleiro.length && finalY < tabuleiro[0].length) {
      if (tabuleiro[finalX][finalY] == -1) {
        return false;
      } else if (finalX == inicialX + 1 && finalY == inicialY) {
        return true;
      } else if (finalX == inicialX - 1 && finalY == inicialY) {
        return true;
      } else if (finalY == inicialY + 1 && finalX == inicialX) {
        return true;
      } else if (finalY == inicialY - 1 && finalX == inicialX) {
        return true;
      }
    }
    return false;
  }

  public static int calculaPontos(int tabuleiro[][], int posicaoX, int posicaoY) {
    int pontos = 0;

    if (tabuleiro[posicaoX][posicaoY] == 5) {
      pontos = 5;
    } else if (tabuleiro[posicaoX][posicaoY] == 15) {
      pontos = 15;
    }
    return pontos;
  }

  public static void imprimeMapa(int tabuleiro[][]) {
    System.out.println("          0 1 2 3 4 5 6 7 8 9");
    for (int linha = 0; linha < 10; linha++) {
      System.out.print("        " + linha + " ");
      for (int coluna = 0; coluna < 10; coluna++) {
        if (tabuleiro[linha][coluna] == -1) {
          System.out.print("X ");
        } else if (tabuleiro[linha][coluna] == 1) {
          System.out.print("🧍 ");
        } else if (tabuleiro[linha][coluna] == 0 || tabuleiro[linha][coluna] == 15) {
          System.out.print("… ");
        } else {
          System.out.print("🍺 ");
        }
      }
      System.out.println("");
    }

  }

  public static void main(String[] args) {
    int tabuleiro[][] = new int[10][10];
    String nome, repetirNome;
    int coordX, coordY, inicialX, inicialY;
    boolean verificacao;
    int pontos, pontuacaoTotal;
    int movimentosTotais;
    float score;
    String resp,caractere1,demaisCarac,resposta="Sim";

    Scanner n = new Scanner(System.in);

    while (resposta.equals("Sim")) {

      for (int linha = 0; linha < 10; linha++) {
        for (int coluna = 0; coluna < 10; coluna++) {
          tabuleiro[linha][coluna] = 0;
        }
      }

      tabuleiro[7][0] = 5;
      tabuleiro[6][2] = 5;
      tabuleiro[2][4] = 5;
      tabuleiro[8][6] = 5;
      tabuleiro[4][9] = 5;
      tabuleiro[2][0] = 15;
      tabuleiro[1][3] = 15;
      tabuleiro[1][2] = 15;
      tabuleiro[9][8] = 15;
      
      coordX=0;
      coordY=0;
      inicialX=0;
      inicialY=0;
      pontuacaoTotal=0;
      movimentosTotais=0;
      tabuleiro[inicialX][inicialY] = 1;

      System.out.println("----- Snake 2.0 -----");
      System.out.println("Versao cachaceiro!!");
      System.out.println("Diretamente do Nordeste!!");
      System.out.println("------------------------------------------------------------");
      System.out.println("Primeiramente cadastre-se!");
      System.out.print("Insira seu nome: ");
      nome = n.nextLine();
      nome = nome.toLowerCase();
      while (nome.length() < 7) {
        System.out.println("O nome precisa ter no minimo 7 caracteres!");
        System.out.print("Insira seu nome: ");
        nome = n.nextLine();
        nome = nome.toLowerCase();
      }

      System.out.print("Insira novamente seu nome: ");
      repetirNome = n.nextLine();
      repetirNome = repetirNome.toLowerCase();

      while (nome.equals(repetirNome) == false) {
        System.out.println("Os nomes precisam ser compativeis!!");
        System.out.print("Insira seu nome: ");
        nome = n.nextLine();
        nome = nome.toLowerCase();
        while (nome.length() < 7) {
          System.out.println("O nome precisa ter no minimo 7 caracteres!");
          System.out.print("Insira seu nome: ");
          nome = n.nextLine();
          nome = nome.toLowerCase();
        }

        System.out.print("Insira novamente seu nome: ");
        repetirNome = n.nextLine();
        repetirNome = repetirNome.toLowerCase();
      }
      System.out.println("");
      System.out.println("VAMO LA!! AGORA MOVIMENTE O SEU PINGUCO INTERIOR!!");

      tabuleiro[coordX][coordY] = 1;
      imprimeMapa(tabuleiro);

      while (tabuleiro[7][0] == 5 || tabuleiro[6][2] == 5 || tabuleiro[2][4] == 5 || tabuleiro[8][6] == 5  || tabuleiro[4][9] == 5) {

        tabuleiro[coordX][coordY] = -1;
        inicialX = coordX;
        inicialY = coordY;

        System.out.print("Digite a coordenada X para o deslocamento: ");
        coordX = n.nextInt();
        System.out.print("Digite a coordenada Y para o deslocamento: ");
        coordY = n.nextInt();

        verificacao = verificaMovimento(tabuleiro, inicialX, inicialY, coordX, coordY);

        while (verificacao == false) {
          System.out.println("ERRO! por favor digite as coordenadas novamente!");
          System.out.print("Digite a coordenada X para o deslocamento: ");
          coordX = n.nextInt();
          System.out.print("Digite a coordenada Y para o deslocamento: ");
          coordY = n.nextInt();

          verificacao = verificaMovimento(tabuleiro, inicialX, inicialY, coordX, coordY);
        }

        movimentosTotais += 1;

        pontos= calculaPontos(tabuleiro, coordX, coordY);

        if (pontos == 15) {
          System.out.println("------------------------------------------------------------");
          System.out.println("Parabens, voce encontrou uma dose de pitu escondida!!");
          System.out.println("+15 pontos");
          System.out.println("------------------------------------------------------------");
          pontuacaoTotal += 15;
        } else if (pontos == 5) {

          System.out.println("");
          System.out.println("Voce alcancou uma cerveja!!");
          System.out.println("+5 pontos");
          System.out.println("");
          pontuacaoTotal += 5;
        } else {
          pontuacaoTotal += 0;
        }

        tabuleiro[coordX][coordY] = 1;
        imprimeMapa(tabuleiro);

      }

      score = pontuacaoTotal / (float) movimentosTotais;

      System.out.println("------------------------------------------------------------");
      System.out.println("Fim de jogo!");
      System.out.println("jogador: " + nome);
      System.out.println("Score(pontuacao): " + score);

      System.out.println("");
      System.out.println("Deseja jogar novamente? (sim/nao)");
      resp = n.next();

      caractere1 = resp.substring(0, 1);
      demaisCarac = resp.substring(1, 3);
      resposta = caractere1.toUpperCase() + demaisCarac;

    }
    System.out.println("Fim de Jogo!");
  }

}