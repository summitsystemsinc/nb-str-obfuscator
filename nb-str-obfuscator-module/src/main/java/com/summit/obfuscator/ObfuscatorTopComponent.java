/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.obfuscator;

import de.schlichtherle.util.ObfuscatedString;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.modules.csl.spi.GsfUtilities;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.text.CloneableEditorSupport;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//com.summit.obfuscator//Obfuscator//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "ObfuscatorTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "com.summit.obfuscator.ObfuscatorTopComponent")
@ActionReference(path = "Menu/Window" , position = 333)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_ObfuscatorAction",
preferredID = "ObfuscatorTopComponent")
@Messages({
    "CTL_ObfuscatorAction=Obfuscator",
    "CTL_ObfuscatorTopComponent=Obfuscator",
    "HINT_ObfuscatorTopComponent=Static String Obfuscator"
})
public final class ObfuscatorTopComponent extends TopComponent {

    public ObfuscatorTopComponent() {
        initComponents();
        setName(Bundle.CTL_ObfuscatorTopComponent());
        setToolTipText(Bundle.HINT_ObfuscatorTopComponent());


        File tmpFile;
        try {
            FileObject fob = FileUtil.createMemoryFileSystem().getRoot().createData("tempJava.java");
            
            Document doc = GsfUtilities.getDocument(fob, true);
            String mimeType = "text/x-java";
            obfuscatedCodePane.setContentType(mimeType);
            EditorKit kit = MimeLookup.getLookup(mimeType).lookup(EditorKit.class);
            obfuscatedCodePane.setEditorKit(kit);
            obfuscatedCodePane.setDocument(doc);
            obfuscatedCodePane.getDocument().putProperty("mimeType", mimeType);
            
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        originalTextField = new javax.swing.JTextField();
        obfuscateButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        obfuscatedCodePane = new javax.swing.JEditorPane();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ObfuscatorTopComponent.class, "ObfuscatorTopComponent.jLabel1.text")); // NOI18N

        originalTextField.setText(org.openide.util.NbBundle.getMessage(ObfuscatorTopComponent.class, "ObfuscatorTopComponent.originalTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(obfuscateButton, org.openide.util.NbBundle.getMessage(ObfuscatorTopComponent.class, "ObfuscatorTopComponent.obfuscateButton.text")); // NOI18N
        obfuscateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obfuscateButtonActionPerformed(evt);
            }
        });

        obfuscatedCodePane.setContentType("text/x-java"); // NOI18N
        obfuscatedCodePane.setEditorKit(CloneableEditorSupport.getEditorKit("text/x-java"));
        jScrollPane1.setViewportView(obfuscatedCodePane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(originalTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(obfuscateButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(originalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(obfuscateButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void obfuscateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obfuscateButtonActionPerformed
        String text = originalTextField.getText();
        if (text != null) {
            String obfsCode = ObfuscatedString.obfuscate(text);
            obfuscatedCodePane.setText(obfsCode);
        }
    }//GEN-LAST:event_obfuscateButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton obfuscateButton;
    private javax.swing.JEditorPane obfuscatedCodePane;
    private javax.swing.JTextField originalTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
