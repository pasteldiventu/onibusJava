import java.util.*;

class Onibus {
    private final String nome;
    private int posicao;
    private final Random random = new Random();

    public Onibus(String nome) {
        this.nome = nome;
        this.posicao = 0;
        this.emCorrida = true; 
    }

    public void correr() {
        int passo = random.nextInt(10) + 1;
        posicao += passo;
        if (posicao > 100) posicao = 100;
    }

    public String getNome() {
        return nome;
    }

    public int getPosicao() {
        return posicao;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Onibus> busoes = List.of(
            new Onibus("Ônibus Azul"),
            new Onibus("Ônibus Vermelho"),
            new Onibus("Ônibus Verde")
        );

        boolean corridaFinalizada = false;
        Onibus vencedor = null;

        while (!corridaFinalizada) {
            System.out.print("\033[H\033[2J"); // limpa terminal ANSI
            System.out.flush();

            for (Onibus o : busoes) {
                o.correr();
                String pista = " ".repeat(o.getPosicao()) + "🚌";
                System.out.printf("%-17s: %s\n", o.getNome(), pista);

                if (o.getPosicao() >= 100 && vencedor == null) {
                    vencedor = o;
                    corridaFinalizada = true;
                }
            }

            Thread.sleep(300);
        }

        System.out.println("\n🏁 Vencedor: " + vencedor.getNome() + "!\n");
    }
}
