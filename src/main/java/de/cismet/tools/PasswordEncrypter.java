/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.tools;

import net.sourceforge.blowfishj.BlowfishEasy;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * DOCUMENT ME!
 *
 * @author   thorsten.hell@cismet.de
 * @version  $Revision$, $Date$
 */
public class PasswordEncrypter extends javax.swing.JFrame {

    //~ Static fields/initializers ---------------------------------------------

    private static final char[] MASTER_PASS = "fourtytwo".toCharArray(); // NOI18N
    public static String CRYPT_PREFIX = "crypt::";                       // NOI18N
    private static final transient org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(
            PasswordEncrypter.class);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdGo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField pwfPassword1;
    private javax.swing.JPasswordField pwfPassword2;
    private javax.swing.JTextArea txtCode;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form PasswordEncrypter.
     */
    public PasswordEncrypter() {
        initComponents();
        getRootPane().setDefaultButton(cmdGo);
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
        txtCode = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pwfPassword1 = new javax.swing.JPasswordField();
        pwfPassword2 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(PasswordEncrypter.class, "PasswordEncrypter.title")); // NOI18N
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

        txtCode.setLineWrap(true);
        txtCode.setRows(3);
        txtCode.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(txtCode, gridBagConstraints);

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

        final java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 398) / 2, (screenSize.height - 183) / 2, 398, 183);
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void pwfPassword2FocusGained(final java.awt.event.FocusEvent evt) { //GEN-FIRST:event_pwfPassword2FocusGained
        pwfPassword2.setSelectionStart(0);
        pwfPassword2.setSelectionEnd(pwfPassword1.getPassword().length);
    }                                                                           //GEN-LAST:event_pwfPassword2FocusGained

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void pwfPassword1FocusGained(final java.awt.event.FocusEvent evt) { //GEN-FIRST:event_pwfPassword1FocusGained
        pwfPassword1.setSelectionStart(0);
        pwfPassword1.setSelectionEnd(pwfPassword1.getPassword().length);
    }                                                                           //GEN-LAST:event_pwfPassword1FocusGained

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmdGoActionPerformed(final java.awt.event.ActionEvent evt) {                        //GEN-FIRST:event_cmdGoActionPerformed
        final String p1 = new String(pwfPassword1.getPassword());
        final String p2 = new String(pwfPassword2.getPassword());
        if (p1.equals(p2)) {
            txtCode.setText(encryptString(p1));
            // System.out.println(decryptString(txtCode.getText()));
        } else {
            JOptionPane.showMessageDialog(
                this,
                org.openide.util.NbBundle.getMessage(
                    PasswordEncrypter.class,
                    "PasswordEncrypter.cmdGoActionPerformed(ActionEvent).JOptionPane_anon.message"), // NOI18N
                org.openide.util.NbBundle.getMessage(
                    PasswordEncrypter.class,
                    "PasswordEncrypter.cmdGoActionPerformed(ActionEvent).JOptionPane_anon.title"),   // NOI18N
                JOptionPane.ERROR_MESSAGE);
            pwfPassword1.setText("");                                                                // NOI18N
            pwfPassword2.setText("");                                                                // NOI18N
        }
    }                                                                                                //GEN-LAST:event_cmdGoActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  args  the command line arguments
     */
    public static void main(final String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new PasswordEncrypter().setVisible(true);
                }
            });
    }

    /**
     * DOCUMENT ME!
     *
     * @param   code  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static String decryptString(String code) {
        if ((code != null) && code.startsWith(CRYPT_PREFIX)) {
            final BlowfishEasy blowfish = new BlowfishEasy(MASTER_PASS);
            code = code.substring(CRYPT_PREFIX.length(), code.length());
            return blowfish.decryptString(code);
        } else {
            return code;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   pwd  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static String encryptString(final String pwd) {
        final BlowfishEasy blowfish = new BlowfishEasy(MASTER_PASS);
        try {
            final String code = CRYPT_PREFIX + blowfish.encryptString(pwd);
            return code;
        } catch (Exception e) {
            log.warn("Error during encryption of STRING. Attention will use plain STRING instead.", e);
            return pwd;
        }
    }
}
