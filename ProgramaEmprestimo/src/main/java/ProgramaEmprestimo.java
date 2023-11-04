import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class ProgramaEmprestimo {

    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Nome do colaborador:");
        String dataAdmissaoStr = JOptionPane.showInputDialog("Data de admissão (dd/MM/yyyy):");
        Date dataAdmissao = parseDate(dataAdmissaoStr);
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Salário atual:"));
        double valorEmprestimo = Double.parseDouble(JOptionPane.showInputDialog("Valor de empréstimo desejado:"));

        boolean atendeRequisitos = isEligible(dataAdmissao) && isMultipleOfTwo(valorEmprestimo);

        if (atendeRequisitos) {
            List<Integer> notasMaiores = calcularNotasMaiores(valorEmprestimo);
            List<Integer> notasMenores = calcularNotasMenores(valorEmprestimo);
            List<Integer> notasMeioAMeioMaiores = calcularNotasMaiores(valorEmprestimo / 2);
            List<Integer> notasMeioAMeioMenores = calcularNotasMenores(valorEmprestimo / 2);

            String resultado = "Valor empréstimo: " + valorEmprestimo + " reais\n";
            resultado += "Notas de maior valor:\n";
            resultado += formatNotas(notasMaiores);

            resultado += "\nNotas de menor valor:\n";
            resultado += formatNotas(notasMenores);

            resultado += "\nNotas meio a meio (maior valor):\n";
            resultado += formatNotas(notasMeioAMeioMaiores);

            resultado += "\nNotas meio a meio (menor valor):\n";
            resultado += formatNotas(notasMeioAMeioMenores);

            JOptionPane.showMessageDialog(null, resultado);
        } else {
            if (!isEligible(dataAdmissao)) {
                JOptionPane.showMessageDialog(null, "Agradecemos seu interesse, mas você não atende os requisitos mínimos do programa.");
            } else {
                JOptionPane.showMessageDialog(null, "Insira um valor válido!");
            }
        }
    }

    private static Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean isEligible(Date dataAdmissao) {
        Date dataAtual = new Date();
        long diff = dataAtual.getTime() - dataAdmissao.getTime();
        long anos = diff / (365L * 24L * 60L * 60L * 1000L);
        return anos >= 5;
    }

    private static boolean isMultipleOfTwo(double valor) {
        if (valor % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static List<Integer> calcularNotasMaiores(double valor) {
        List<Integer> notas = new ArrayList<>();
        int[] valoresNotas = {200, 100, 50};
        for (int nota : valoresNotas) {
            int quantidade = (int) (valor / nota);
            notas.add(quantidade);
            valor -= nota * quantidade;
        }
        return notas;
    }

    private static List<Integer> calcularNotasMenores(double valor) {
        List<Integer> notas = new ArrayList<>();
        int[] valoresNotas = {20, 10, 5, 2};
        for (int nota : valoresNotas) {
            int quantidade = (int) (valor / nota);
            notas.add(quantidade);
            valor -= nota * quantidade;
        }
        return notas;
    }

    private static String formatNotas(List<Integer> notas) {
        String resultado = "";
        for (int i = 0; i < notas.size(); i++) {
            int nota = notas.get(i);
            if (nota > 0) {
                resultado += "➢ " + nota + " x " + ((i % 2 == 0) ? "100" : (i == 1 ? "50" : (i == 2 ? "20" : (i == 3 ? "10" : (i == 4 ? "5" : "2")))) + " reais;\n");
            }
        }
        return resultado;
    }
}
