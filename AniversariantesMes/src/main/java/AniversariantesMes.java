import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AniversariantesMes {
    public static void main(String[] args) {
        //C:\\caminho\\para\\o\\seu\\diretorio\\dados_consultores.txt
        String arquivoEntrada = "C:\\Users\\Joao_\\OneDrive\\Área de Trabalho\\Cristiane\\Documentos\\NetBeansProjects\\AniversariantesMes\\src\\main\\java\\dados_consultores.txt";
        //C:\\caminho\\para\\o\\seu\\diretorio\\aniversariantes_do_mes.txt
        String arquivoSaida = "C:\\Users\\Joao_\\OneDrive\\Área de Trabalho\\Cristiane\\Documentos\\NetBeansProjects\\AniversariantesMes\\src\\main\\java\\aniversariantes_do_mes.txt";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAtual = new Date();
        int mesCorrente = dataAtual.getMonth() + 1; // O mês é base 0, então somamos 1 para obter o mês atual.

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoEntrada));
             BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {

            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|"); // Separar os dados por pipe (|)

                if (partes.length >= 3) {
                    String nome = partes[0];
                    String email = partes[1];
                    Date dataNascimento = dateFormat.parse(partes[2]);

                    if (dataNascimento.getMonth() + 1 == mesCorrente) {
                        // É um aniversariante do mês corrente, escreva as informações no arquivo de saída.
                        writer.write("Nome: " + nome + "\n");
                        writer.write("E-mail: " + email + "\n");
                        writer.write("Data de Nascimento: " + dateFormat.format(dataNascimento) + "\n");
                        writer.write("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Arquivo de aniversariantes do mês foi gerado: " + arquivoSaida);
    }
}
