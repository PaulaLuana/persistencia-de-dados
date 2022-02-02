import dao.ArticleDAO;
import model.Article;
import java.util.ArrayList;
import java.util.List;

public class Insert {
    //Faz a inserção no banco
    public static void main(String[] args) {
        ArticleDAO dao = new ArticleDAO();

        List<String> tags = new ArrayList<String>();
        tags.add("implementacao");
        tags.add("teste");
        tags.add("tag a mais");
        Article art1 = new Article(1, "nome tal" ,"001","data","2011-09-02", tags);
        dao.insert(art1);

        tags.clear();
        tags.add("persistencia de dados");
        tags.add("teste");
        tags.add("insercao");
        Article art2 = new Article(2, "artigo2" ,"002","data2","2022-01-01", tags);
        dao.insert(art2);

        tags.clear();
        tags.add("tag_nova");
        tags.add("tag_new");
        Article art3 = new Article(3, "artigo3" ,"003","data3","2022-02-01", tags);
        dao.insert(art3);





    }
}
