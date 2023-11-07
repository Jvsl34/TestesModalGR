import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import javax.swing.JOptionPane;

public class SenhaCrypt {

    private static final String secretKey = "#modalGR#GPTW#top#maiorEmpresaTecnologia#baixadaSantista";

    public static String senhaCriptografada(String password) {
        try {
            SecretKey key = generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String senhaDescriptografada(String encryptedPassword) {
        try {
            SecretKey key = generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static SecretKey generateKey() throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(secretKey.getBytes());
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static void main(String[] args) {
        // Exemplo de uso
        String originalPassword = JOptionPane.showInputDialog("Digite a senha 1: ");
        String originalPassword2 = JOptionPane.showInputDialog("Digite a senha 2: ");
        String originalPassword3 = JOptionPane.showInputDialog("Digite a senha 3: ");
        String encryptedPassword = senhaCriptografada(originalPassword);
        String encryptedPassword2 = senhaCriptografada(originalPassword2);
        String encryptedPassword3 = senhaCriptografada(originalPassword3);
        JOptionPane.showMessageDialog(null, "Senhas Criptografada:\n\n" + encryptedPassword + "\n\n" + encryptedPassword2 + "\n\n" + encryptedPassword3);
        

        String decryptedPassword = senhaDescriptografada(encryptedPassword);
        String decryptedPassword2 = senhaDescriptografada(encryptedPassword2);
        String decryptedPassword3 = senhaDescriptografada(encryptedPassword3);
        JOptionPane.showMessageDialog(null, "Senhas Descriptografada:\n\n" + decryptedPassword + "\n\n" + decryptedPassword2 + "\n\n" + decryptedPassword3);
    }
}
