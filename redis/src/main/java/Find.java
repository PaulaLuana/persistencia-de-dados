import dao.ArticleDAO;

import javax.swing.*;

public class Find {
    public static void main(String[] args) {
        //Classe responsável por fazer as buscas nos artigos já criados
        //Caso os artigos não existam, execute a classe Insert para inseri-los.

        Principal();


    }

    public static char menuPrincipal(){
        String menu = "Escolha uma opção:\n" +
                "1 - Todas as tags de um determinado artigo.\n" +
                "2 - Todos os artigos de uma determinada tag.\n" +
                "3 - Nome e descrição de todos os artigos.\n" +
                "4 - Sair";
        return JOptionPane.showInputDialog(menu).charAt(0);
    }

    public static void Principal() {
        ArticleDAO dao = new ArticleDAO();
        char menu = '0';
        String campo;

        while (menu != '4') {
            menu = menuPrincipal();
            switch (menu) {
                case '1'://Todas as tags de um determinado artigo
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Id do artigo: "));
                    JOptionPane.showMessageDialog(null, dao.tags(id));
                    break;

                case '2': //Todos os artigos de uma determinada tag
                    String tag = JOptionPane.showInputDialog("Tag: ");
                    JOptionPane.showMessageDialog(null, dao.findByTag(tag));
                    break;

                case '3'://Nome e descrição de todos os artigos
                    JOptionPane.showMessageDialog(null, dao.findAll());
                    break;

                case '4':
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");

            }
        }
    }

}
