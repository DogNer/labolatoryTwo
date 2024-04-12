import Model.StuctOfGroup;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class InforamtionWindow extends JFrame{
    private JPanel panel1;
    private JButton btnBack;
    private JTree treeGroup;
    private JPanel panelTree;
    private JScrollPane scroll;

    public InforamtionWindow(DefaultTreeModel treeModel)  throws HeadlessException {
        this.setSize(500, 300);
        this.add(panel1);

        treeGroup.setModel(treeModel);

        if(treeGroup != null) {
            JTree tree = new JTree(treeModel);
            scroll.add(tree);
        }

        treeGroup.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeGroup.getLastSelectedPathComponent();
                if (selectedNode == null) {
                    return;
                }

                String selectedNodeName = selectedNode.getUserObject().toString();
                if (selectedNode.isLeaf()) {
                    for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
                        if (StuctOfGroup.arrayGoods.get(i).getName().equals(selectedNodeName)) {
                            JOptionPane.showMessageDialog(null, StuctOfGroup.arrayGoods.get(i).outputFormat());
                            return;
                        }
                    }
                }
            }
        });
        btnBack.addActionListener(e -> {
            this.setVisible(false);
        });
    }

}
