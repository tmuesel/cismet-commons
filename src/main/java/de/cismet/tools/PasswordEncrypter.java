/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.tools;

import net.sourceforge.blowfishj.BlowfishEasy;

import org.apache.log4j.Logger;

import org.openide.util.NbBundle;
import org.openide.util.WeakListeners;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.io.IOException;
import java.io.InputStream;

import java.security.SecureRandom;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Applet Password Encrypter
 *
 * @author   thorsten.hell@cismet.de
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public class PasswordEncrypter extends javax.swing.JFrame {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient Logger LOG = Logger.getLogger(PasswordEncrypter.class);

    private static final byte LF = 0xA;

    @Deprecated
    public static String CRYPT_PREFIX = "crypt::";                       // NOI18N
    @Deprecated
    private static final char[] MASTER_PASS = "fourtytwo".toCharArray(); // NOI18N

    private static final String CIPHER = "PBEWithMD5AndDES/CBC/PKCS5Padding"; // NOI18N
    private static final String FACTORY = "PBEWithMD5AndDES";                 // NOI18N
    private static final int ITERATIONS = 20;
    private static final byte[] DEFAULT_SALT = new byte[] { 124, 10, 10, 54, 23, 43, 72, 78 };

    private static final char[] PE_MASTERKEY_PROP = "PasswordEncrypter.masterKey".toCharArray(); // NOI18N
    private static final char[] PE_SALT_PROP = "PasswordEncrypter.salt".toCharArray();           // NOI18N

    private static final SecureRandom RANDOM = new SecureRandom();

    //~ Instance fields --------------------------------------------------------

    private final FocusListener codeFocusL;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkLegacy;
    private javax.swing.JButton cmdGo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField pwfPassword1;
    private javax.swing.JPasswordField pwfPassword2;
    private javax.swing.JTextArea txaCode;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form PasswordEncrypter.
     */
    public PasswordEncrypter() {
        this.codeFocusL = new CodeFocusListener();

        initComponents();

        getRootPane().setDefaultButton(cmdGo);
        txaCode.addFocusListener(WeakListeners.create(FocusListener.class, codeFocusL, txaCode));
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        cmdGo = new javax.swing.JButton();
        txaCode = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pwfPassword1 = new javax.swing.JPasswordField();
        pwfPassword2 = new javax.swing.JPasswordField();
        chkLegacy = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(PasswordEncrypter.class, "PasswordEncrypter.title")); // NOI18N
        setFocusTraversalPolicy(new FocusTraversalOrder());
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("MS Sans Serif", 1, 12)); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(
                PasswordEncrypter.class,
                "PasswordEncrypter.jLabel1.text"));                 // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 11, 3);
        getContentPane().add(jLabel1, gridBagConstraints);

        cmdGo.setText(org.openide.util.NbBundle.getMessage(PasswordEncrypter.class, "PasswordEncrypter.cmdGo.text")); // NOI18N
        cmdGo.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmdGoActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(cmdGo, gridBagConstraints);

        txaCode.setLineWrap(true);
        txaCode.setRows(3);
        txaCode.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(txaCode, gridBagConstraints);

        jLabel2.setText(org.openide.util.NbBundle.getMessage(
                PasswordEncrypter.class,
                "PasswordEncrypter.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText(org.openide.util.NbBundle.getMessage(
                PasswordEncrypter.class,
                "PasswordEncrypter.jLabel3.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        jLabel4.setText(org.openide.util.NbBundle.getMessage(
                PasswordEncrypter.class,
                "PasswordEncrypter.jLabel4.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        getContentPane().add(jLabel4, gridBagConstraints);

        pwfPassword1.addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusGained(final java.awt.event.FocusEvent evt) {
                    pwfPassword1FocusGained(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(pwfPassword1, gridBagConstraints);

        pwfPassword2.addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusGained(final java.awt.event.FocusEvent evt) {
                    pwfPassword2FocusGained(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(pwfPassword2, gridBagConstraints);

        chkLegacy.setSelected(true);
        chkLegacy.setText(NbBundle.getMessage(PasswordEncrypter.class, "PasswordEncrypter.chkLegacy.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        getContentPane().add(chkLegacy, gridBagConstraints);

        final java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 398) / 2, (screenSize.height - 183) / 2, 398, 183);
    } // </editor-fold>//GEN-END:initComponents

    /**
     * Focus Gained Password2
     *
     * @param  evt  Event
     */
    private void pwfPassword2FocusGained(final java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwfPassword2FocusGained
        pwfPassword2.setSelectionStart(0);
        pwfPassword2.setSelectionEnd(pwfPassword1.getPassword().length);
    }//GEN-LAST:event_pwfPassword2FocusGained

    /**
     * Focus Gained Password1
     *
     * @param  evt  Event
     */
    private void pwfPassword1FocusGained(final java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwfPassword1FocusGained
        pwfPassword1.setSelectionStart(0);
        pwfPassword1.setSelectionEnd(pwfPassword1.getPassword().length);
    }//GEN-LAST:event_pwfPassword1FocusGained

    /**
     * Starter
     *
     * @param  evt  Event
     */
    private void cmdGoActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGoActionPerformed
        final String p1 = new String(pwfPassword1.getPassword());
        final String p2 = new String(pwfPassword2.getPassword());
        if (p1.equals(p2)) {
            try {
                final String enc;
                if (chkLegacy.isSelected()) {
                    enc = encryptString(String.valueOf(pwfPassword1.getPassword()));
                } else {
                    enc = String.valueOf(encrypt(pwfPassword1.getPassword(), false));
                }
                txaCode.setText(enc);
            } catch (final PasswordEncrypterException ex) {
                Throwable cause = ex;
                Throwable current = ex;
                while (current != null) {
                    cause = current;
                    current = current.getCause();
                }
                txaCode.setText(NbBundle.getMessage(
                        PasswordEncrypter.class,
                        "PasswordEncrypter.cmdGoActionPerformed(ActionEvent).txtCode.text.pwEncEx",  // NOI18N
                        cause.getLocalizedMessage()));
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                NbBundle.getMessage(
                    PasswordEncrypter.class,
                    "PasswordEncrypter.cmdGoActionPerformed(ActionEvent).JOptionPane_anon.message"), // NOI18N
                NbBundle.getMessage(
                    PasswordEncrypter.class,
                    "PasswordEncrypter.cmdGoActionPerformed(ActionEvent).JOptionPane_anon.title"),   // NOI18N
                JOptionPane.ERROR_MESSAGE);
            pwfPassword1.setText("");                                                                // NOI18N
            pwfPassword2.setText("");                                                                // NOI18N
        }
    }//GEN-LAST:event_cmdGoActionPerformed

    /**
     * main
     *
     * @param   args  the command line arguments
     *
     * @throws  Exception
     */
    public static void main(final String[] args) throws Exception {
        java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                    new PasswordEncrypter().setVisible(true);
                }
            });
    }

    /**
     * Decrypts <code>String</code> with BlowfishEasy
     *
     * @param   code  <code>String</code> that should get decrypted
     *
     * @return  Return decrypted code or <code>null</code> if code was set to <code>null</code>
     *
     * @throws  PasswordEncrypterException  DOCUMENT ME!
     */
    @Deprecated
    public static String decryptString(String code) throws PasswordEncrypterException {
        if ((code != null) && code.startsWith(CRYPT_PREFIX)) {
            final BlowfishEasy blowfish = new BlowfishEasy(MASTER_PASS);
            code = code.substring(CRYPT_PREFIX.length(), code.length());
            return blowfish.decryptString(code);
        } else {
            return code;
        }
    }

    /**
     * Encrypts a specified <code>String</code> using BlowfishEasy.
     *
     * @param   pwd  <code>String</code> that shoudl get encrypted
     *
     * @return  encrypted <code>String</code>
     *
     * @throws  PasswordEncrypterException  DOCUMENT ME!
     */
    @Deprecated
    public static String encryptString(final String pwd) throws PasswordEncrypterException {
        final BlowfishEasy blowfish = new BlowfishEasy(MASTER_PASS);
        final String code = CRYPT_PREFIX + blowfish.encryptString(pwd);

        return code;
    }

    /**
     * Encrypts a given string using Password-based Encryption with MD5 and DES that can be decrypted using
     * {@link #decrypt(char[], boolean)}. The caller is responsible for wiping the returned result himself. The given
     * array can automatically be wiped by passing <code>true</code> to the <code>wipeInput</code> parameter. All
     * temporary created data is immediately wiped, thus this implementation offers as little traces in memory as
     * possible.<br/>
     * <br/>
     * <b>IMPORTANT: This operations requires the<br/>
     * <br/>
     * <code>PasswordEncrypter.properties</code><br/>
     * <br/>
     * file containing the property<br/>
     * <br/>
     * <code>PasswordEncrypter.masterKey</code><br/>
     * <br/>
     * to be located on the classpath within the package<br/>
     * <br/>
     * <code>de.cismet.tools</code><br/>
     * <br/>
     * </b> . Currently, it is also required, that the password only contains <code>ASCII</code> characters, see
     * {@link #safeRead(java.io.InputStream, char[])}. This operation also requires <code>Salt</code> as an input.
     * However, though it is not recommended, this implementation offers a default-Salt, if none is provided. The Salt
     * can be provided through the <code>PasswordEncrypter.salt</code> property within the <code>
     * PasswordEncrypter.properties</code> in the <code>de.cismet.tools</code> package. It must have a fixed length of
     * eight byte. If it is too small, missing bytes will be filled up using the default-Salt. If it is too big, only
     * the first eight bytes are used. Every character provided by the Salt property is interpreted as a <i>single</i>
     * byte, see <code>safeRead(java.io.InputStream, char[])</code>.
     *
     * @param   string     the string to encrypt
     * @param   wipeInput  whether to wipe the input array or not
     *
     * @return  the base64 encoded encrypted string in a <code>char[]</code> or <code>null</code> if the given array is
     *          <code>null</code>
     *
     * @throws  PasswordEncrypterException  if an error occurs during encryption, e.g. the PasswordEncrypter.properties
     *                                      with the masterKey cannot be found, etc.
     *
     * @see     #safeRead(java.io.InputStream, char[])
     */
    public static char[] encrypt(final char[] string, final boolean wipeInput) throws PasswordEncrypterException {
        if (string == null) {
            LOG.warn("received null array, returning null"); // NOI18N

            return null;
        }

        final byte[] bytes = bytesFromChars(string, wipeInput);

        final byte[] enc = applyCipher(bytes, Cipher.ENCRYPT_MODE);

        wipe(bytes);

        final byte[] base64 = Base64.toBase64(enc, true);
        final char[] chars = new char[base64.length];
        for (int i = 0; i < base64.length; ++i) {
            chars[i] = (char)base64[i];
            base64[i] = getWipe();
        }

        final char[] ret = new char[chars.length + 2];

        ret[0] = '{';
        for (int i = 0; i < chars.length; ++i) {
            ret[i + 1] = chars[i];
            chars[i] = (char)getWipe();
        }
        ret[ret.length - 1] = '}';

        return ret;
    }

    /**
     * Decrypts a given string that was created by {@link #encrypt(char[])}. The caller is responsible for wiping the
     * returned result himself. The given array can automatically be wiped by passing <code>true</code> to the <code>
     * wipeInput</code> parameter. All temporary created data is immediately wiped, thus this implementation offers as
     * little traces in memory as possible.<br/>
     * <br/>
     * NOTE: this operation can decrypt strings created by {@link #decryptString(java.lang.String)}, too, for
     * compatibility reasons<br/>
     * <br/>
     * <b>IMPORTANT: This operations requires the<br/>
     * <br/>
     * <code>PasswordEncrypter.properties</code><br/>
     * <br/>
     * file containing the property<br/>
     * <br/>
     * <code>PasswordEncrypter.masterKey</code><br/>
     * <br/>
     * to be located on the classpath within the package<br/>
     * <br/>
     * <code>de.cismet.tools</code><br/>
     * <br/>
     * </b> . Currently, it is also required, that the password only contains <code>ASCII</code> characters, see
     * {@link #safeRead(java.io.InputStream, char[])}. This operation also requires <code>Salt</code> as an input.
     * However, though it is not recommended, this implementation offers a default-Salt, if none is provided. The Salt
     * can be provided through the <code>PasswordEncrypter.salt</code> property within the <code>
     * PasswordEncrypter.properties</code> in the <code>de.cismet.tools</code> package. It must have a fixed length of
     * eight byte. If it is too small, missing bytes will be filled up using the default-Salt. If it is too big, only
     * the first eight bytes are used. Every character provided by the Salt property is interpreted as a <i>single</i>
     * byte, see <code>safeRead(java.io.InputStream, char[])</code>.
     *
     * @param   string     the string to decript
     * @param   wipeInput  whether to wipe the input array or not
     *
     * @return  the decrypted string in a <code>char[]</code> or <code>null</code> if the given array is <code>
     *          null</code> or the given string itself, if the string is not correctly formatted
     *
     * @throws  PasswordEncrypterException  if an error occurs during decryption, e.g. the PasswordEncrypter.properties
     *                                      with the masterKey cannot be found or the given array is not base64 encoded,
     *                                      etc.
     *
     * @see     #safeRead(java.io.InputStream, char[])
     */
    public static char[] decrypt(final char[] string, final boolean wipeInput) throws PasswordEncrypterException {
        if (string == null) {
            LOG.warn("received null array, returning null"); // NOI18N

            return null;
        }

        // for backwards compatibility
        final char[] cryptPrefix = CRYPT_PREFIX.toCharArray();
        boolean compatibilityDecrypt = true;
        if (string.length >= cryptPrefix.length) {
            for (int i = 0; i < cryptPrefix.length; ++i) {
                if (string[i] != cryptPrefix[i]) {
                    compatibilityDecrypt = false;
                    break;
                }
            }
        }

        if (compatibilityDecrypt) {
            return decryptString(String.valueOf(string)).toCharArray();
        } else if (('{' == string[0]) && ('}' == string[string.length - 1])) {
            // strip the curly braces
            final char[] base64 = new char[string.length - 2];
            for (int i = 0; i < (string.length - 2); ++i) {
                base64[i] = string[i + 1];
                if (wipeInput) {
                    string[i] = (char)getWipe();
                }
            }

            if (wipeInput) {
                string[0] = (char)getWipe();
                string[string.length - 1] = (char)getWipe();
            }

            final byte[] b64Bytes = new byte[base64.length];
            for (int i = 0; i < base64.length; ++i) {
                b64Bytes[i] = (byte)base64[i];
                base64[i] = (char)getWipe();
            }

            final byte[] bytes = Base64.fromBase64(b64Bytes, true);

            final byte[] res = applyCipher(bytes, Cipher.DECRYPT_MODE);

            wipe(bytes);

            final char[] ret = charsFromBytes(res, true);

            return ret;
        } else {
            return string;
        }
    }

    /**
     * Applies <code>Cipher</code>
     *
     * @param   bytes  string
     * @param   mode   mode
     *
     * @return  <code>byte</code>
     *
     * @throws  IllegalArgumentException    given byte is null
     * @throws  PasswordEncrypterException  cannot process <code>String</code> mode
     */
    private static byte[] applyCipher(final byte[] bytes, final int mode) {
        if (bytes == null) {
            throw new IllegalArgumentException("given bytes must not be null"); // NOI18N
        }

        Cipher pbeCipher = null;
        SecretKey pbeKey = null;
        try {
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(FACTORY);
            pbeKey = keyFactory.generateSecret(new PBEKeySpec(getMasterPw()));
            pbeCipher = Cipher.getInstance(CIPHER);
            pbeCipher.init(mode, pbeKey, new PBEParameterSpec(getSalt(), ITERATIONS));

            final byte[] res = pbeCipher.doFinal(bytes);

            return res;
        } catch (final Exception ex) {
            final String message = "cannot process string: mode=" + mode; // NOI18N
            LOG.error(message, ex);
            throw new PasswordEncrypterException(message, ex);
        } finally {
            // ensure re-init for wiping the cipher, when the cipher is initialised, the key is initialised, too
            if (pbeCipher != null) {
                try {
                    pbeCipher.init(mode, pbeKey, new PBEParameterSpec(getSalt(), ITERATIONS));
                } catch (final Exception ex) {
                    LOG.warn("cannot re-init the cipher", ex); // NOI18N
                }
            }
        }
    }

    /**
     * Getter for Master Password
     *
     * @return  masterpassword as <code>Char[]</code>
     *
     * @throws  PasswordEncrypterException
     * <ul>
     * <li>PasswordEncrypter properties not present.</li>
     * <li>cannot read master password from properties, not set?</li>
     * </ul>
     */
    private static char[] getMasterPw() throws PasswordEncrypterException {
        final InputStream peStream = PasswordEncrypter.class.getResourceAsStream("PasswordEncrypter.properties"); // NOI18N

        if (peStream == null) {
            final String message = "PasswordEncrypter properties not present"; // NOI18N
            LOG.error(message);
            throw new PasswordEncrypterException(message);
        } else {
            // we deal with ASCII only
            final byte[] bytes = safeRead(peStream, PE_MASTERKEY_PROP);
            if (bytes == null) {
                final String message = "cannot read master password from properties, not set?"; // NOI18N
                LOG.error(message);
                throw new PasswordEncrypterException(message);
            }

            // simple cast because of ASCII only support
            final char[] chars = new char[bytes.length];
            for (int i = 0; i < bytes.length; ++i) {
                chars[i] = (char)bytes[i];
                bytes[i] = getWipe();
            }

            return chars;
        }
    }

    /**
     * Get Salt.Always 8 bytes.
     * Uses Default Salt, if Salt not set.
     * If Salt is shorter then eight bytes the rest of the Salt will filled with default Salt.
     * If Salt is too long, returns only the first eight bytes
     *
     * @return  salt
     *
     * @throws  PasswordEncrypterException PasswordEncrypter properties not present
     */
    private static byte[] getSalt() throws PasswordEncrypterException {
        final InputStream peStream = PasswordEncrypter.class.getResourceAsStream("PasswordEncrypter.properties"); // NOI18N

        if (peStream == null) {
            final String message = "PasswordEncrypter properties not present"; // NOI18N
            LOG.error(message);
            throw new PasswordEncrypterException(message);
        } else {
            final byte[] salt = safeRead(peStream, PE_SALT_PROP);
            if (salt == null) {
                LOG.warn("salt not set, using default salt");                  // NOI18N

                return DEFAULT_SALT;
            } else if (salt.length < 8) {
                LOG.warn("salt too short, filling up with default salt"); // NOI18N
                final byte[] newSalt = new byte[8];
                for (int i = 0; i < 8; ++i) {
                    if (i < salt.length) {
                        newSalt[i] = salt[i];
                        salt[i] = getWipe();
                    } else {
                        newSalt[i] = DEFAULT_SALT[i];
                    }
                }

                return newSalt;
            } else if (salt.length < 8) {
                LOG.warn("salt too long, stripping first 8 bytes"); // NOI18N
                final byte[] newSalt = new byte[8];
                for (int i = 0; i < salt.length; ++i) {
                    if (i < newSalt.length) {
                        newSalt[i] = salt[i];
                        salt[i] = getWipe();
                    } else {
                        salt[i] = getWipe();
                    }
                }

                return newSalt;
            } else {
                return salt;
            }
        }
    }

    /**
     * Converts a <code>byte[]</code> into a <code>char[]</code> assuming byte i is the high byte and byte i + 2 is the
     * low byte of a two byte char representation. Thus the resulting char[] is half as long as the input array.
     *
     * @param   bytes      bytes to be converted to chars
     * @param   wipeInput  whether to wipe the input array or not
     *
     * @return  an array with chars created from the given bytes
     *
     * @throws  IllegalArgumentException  if the length of the given byte array is not even
     */
    public static char[] charsFromBytes(final byte[] bytes, final boolean wipeInput) {
        if ((bytes.length % 2) != 0) {
            throw new IllegalArgumentException("cannot convert odd number of bytes"); // NOI18N
        }

        final char[] chars = new char[bytes.length >> 1];
        for (int i = 0; i < chars.length; ++i) {
            final int p = i << 1;
            chars[i] = (char)(((bytes[p] & 0x00FF) << 8) + (bytes[p + 1] & 0x00FF));

            if (wipeInput) {
                bytes[p] = getWipe();
                bytes[p + 1] = getWipe();
            }
        }

        return chars;
    }

    /**
     * Wipes a given <code>byte[]</code> using random bytes. The array will only contain a random mess afterwards.
     *
     * @param  bytes  the byte array to wipe
     *
     * @see    #getWipe()
     */
    public static void wipe(final byte[] bytes) {
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = getWipe();
        }
    }

    /**
     * Converts a <code>char[]</code> into a <code>byte[]</code> assuming byte i is the high byte and byte i + 2 is the
     * low byte of a two byte char representation. Thus the resulting byte[] is twice the size of the input array.
     *
     * @param   chars      chars to be converted to bytes
     * @param   wipeInput  whether to wipe the input array or not
     *
     * @return  an array with bytes extracted from the given chars
     */
    public static byte[] bytesFromChars(final char[] chars, final boolean wipeInput) {
        final byte[] bytes = new byte[chars.length << 1];
        for (int i = 0; i < chars.length; ++i) {
            final int p = i << 1;
            bytes[p] = (byte)((chars[i] & 0xFF00) >> 8);
            bytes[p + 1] = (byte)(chars[i] & 0x00FF);

            if (wipeInput) {
                chars[i] = (char)getWipe();
            }
        }

        return bytes;
    }

    /**
     * Wipes a given <code>char[]</code> using random bytes. The array will only contain a random mess afterwards.
     *
     * @param  chars  the char array to wipe
     *
     * @see    #getWipe()
     */
    public static void wipe(final char[] chars) {
        for (int i = 0; i < chars.length; ++i) {
            chars[i] = (char)getWipe();
        }
    }

    /**
     * Get a random byte to wipe data.
     *
     * @return  a random byte
     *
     * @see     SecureRandom
     */
    public static byte getWipe() {
        RANDOM.setSeed(System.nanoTime());
        final byte[] wipe = new byte[1];
        RANDOM.nextBytes(wipe);

        return wipe[0];
    }

    /**
     * Reads a property as safe as possible. This operation is completely char[] based and won't put any property values
     * into {@link String} objects. The given stream will be used as is, it won't be closed nor reset. However, if the
     * {@link InputStream#read()} operation of this implementation will change some markers or similar they won't be
     * reset, too.<br/>
     * <br/>
     *
     * <p>IMPORTANT: Properties are supposed to be separated by a single '=' and terminated by a line feed or EOF.</p>
     * <br/>
     *
     * <p>IMPORTANT: Only ASCII encoded properties are currently supported</p>
     *
     * @param   propertyStream  the stream to read from
     * @param   property        the property to read
     *
     * @return  the value of the property in a <code>byte[]</code>
     *
     * @throws  PasswordEncrypterException  DOCUMENT ME!
     */
    public static byte[] safeRead(final InputStream propertyStream, final char[] property)
            throws PasswordEncrypterException {
        // TODO: optimise parser
        try {
            int c;
            int mark = 0;
            boolean comment = false;
            boolean firstChar = true;

            while ((c = propertyStream.read()) > 0) {
                if (firstChar && (c == '#')) {
                    comment = true;
                    firstChar = false;
                } else if (c == LF) {
                    firstChar = true;
                    comment = false;
                } else if (comment) {
                    firstChar = false;
                } else if ((property.length > mark) && (c == property[mark])) {
                    firstChar = false;
                    mark++;
                } else if ((c == '=') && (property.length == mark)) {
                    // read the property value
                    byte[] bytes = new byte[100];

                    int p;
                    int index = 0;
                    while (((p = propertyStream.read()) > 0) && (p != LF)) {
                        bytes[index] = (byte)p;
                        index++;

                        final byte[] tmp = new byte[bytes.length + 100];
                        if (index == bytes.length) {
                            // resize and wipe
                            for (int i = 0; i < bytes.length; ++i) {
                                tmp[i] = bytes[i];
                                bytes[i] = getWipe();
                            }

                            bytes = tmp;
                        }
                    }

                    // resize
                    final byte[] tmp = new byte[index];
                    for (int i = 0; i < tmp.length; ++i) {
                        // copy and wipe
                        tmp[i] = bytes[i];
                        bytes[i] = getWipe();
                    }

                    return tmp;
                } else {
                    // mismatching property key, reset marker
                    mark = 0;
                    firstChar = false;
                }
            }
        } catch (final IOException ex) {
            final String message = "cannot read password from properties"; // NOI18N
            LOG.error(message, ex);
            throw new PasswordEncrypterException(message, ex);
        }

        return null;
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * FocusTraversalOrder
     *
     * @version  $Revision$, $Date$
     */
    private final class FocusTraversalOrder extends FocusTraversalPolicy {

        //~ Instance fields ----------------------------------------------------

        private final List<Component> order;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new FocusTraversalOrder object.
         */
        public FocusTraversalOrder() {
            order = new ArrayList<Component>(5);
            order.add(pwfPassword1);
            order.add(pwfPassword2);
            order.add(chkLegacy);
            order.add(cmdGo);
            order.add(txaCode);
        }

        //~ Methods ------------------------------------------------------------

        /**
         * Returns the following <code>Component</code> of the specified <code>Component</code>
         * If the specified <code>Component</code> is the Last, Returns the First <code>Component</code>
         * 
         * @param aContainer <code>Container</code>
         * @param aComponent <code>Component</code>
         * 
         * @return following <code>Component</code>
         */
        @Override
        public Component getComponentAfter(final Container aContainer, final Component aComponent) {
            final int index = (order.indexOf(aComponent) + 1) % order.size();

            return order.get(index);
        }
        
        /**
         * Returns the previous <code>Component</code> of the specified <code>Component</code>
         * If the specified <code>Component</code> is the First, Returns the Last <code>Component</code>
         * 
         * @param aContainer <code>Container</code>
         * @param aComponent <code>Component</code>
         * 
         * @return previous <code>Component</code>
         */
        @Override
        public Component getComponentBefore(final Container aContainer, final Component aComponent) {
            int index = order.indexOf(aComponent) - 1;
            if (index < 0) {
                index = order.size() - 1;
            }

            return order.get(index);
        }

        /**
         * Returns the First <code>Component</code>
         * 
         * @param aContainer <code>Container</code>
         * 
         * @return <code>Component</code>
         */
        @Override
        public Component getFirstComponent(final Container aContainer) {
            return order.get(0);
        }

        /**
         * Returns the Last <code>Component</code>
         * 
         * @param aContainer <code>Container</code>
         * 
         * @return <code>Component</code>
         */
        @Override
        public Component getLastComponent(final Container aContainer) {
            return order.get(order.size() - 1);
        }

         /**
         * Returns the First <code>Component</code>
         * 
         * @param aContainer <code>Container</code>
         * 
         * @return <code>Component</code>
         */
        @Override
        public Component getDefaultComponent(final Container aContainer) {
            return order.get(0);
        }
    }

    /**
     * Code Focus Listener
     *
     * @version  $Revision$, $Date$
     */
    private final class CodeFocusListener implements FocusListener {

        //~ Instance fields ----------------------------------------------------

        private int selStart;
        private int selEnd;

        //~ Methods ------------------------------------------------------------

        /**
         * Sets the Focus on <code>txaCode</code> from <code>selStart</code> to <code>selEnd</code>
         * If the Selected Area is Empty, Selects all.
         * 
         * @param e Event
         */
        @Override
        public void focusGained(final FocusEvent e) {
            if (selStart < selEnd) {
                txaCode.setSelectionStart(selStart);
                txaCode.setSelectionEnd(selEnd);
            }
            if (txaCode.getSelectedText() == null) {
                txaCode.selectAll();
            }
        }

        /**
         * Gets the Focus on <code>txaCode</code> and saves the Start to <code>selStart</code> and the End to <code>selEnd</code>
         * 
         * @param e Event
         */
        @Override
        public void focusLost(final FocusEvent e) {
            // preserve selection
            selStart = txaCode.getSelectionStart();
            selEnd = txaCode.getSelectionEnd();
        }
    }
}
