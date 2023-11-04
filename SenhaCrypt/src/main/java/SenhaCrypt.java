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
        String originalPassword = "senha123";
        String encryptedPassword = senhaCriptografada(originalPassword);
        JOptionPane.showMessageDialog(null, "Senha Criptografada: " + encryptedPassword);

        String decryptedPassword = senhaDescriptografada(encryptedPassword);
        JOptionPane.showMessageDialog(null, "Senha Descriptografada: " + decryptedPassword);
    }
}
