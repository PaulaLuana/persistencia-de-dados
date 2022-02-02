package dao;

import model.Article;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArticleDAO {

    public void insert(Article article){
        Jedis jedis = new Jedis();
        jedis.hset("artigo#"+article.getId(), "name", article.getName());
        jedis.hset("artigo#"+article.getId(), "desc", article.getDesc());
        jedis.hset("artigo#"+article.getId(), "file", article.getFile());
        jedis.hset("artigo#"+article.getId(), "data", article.getData());

        for (String tag: article.getTags()) {
            jedis.sadd("artigo#"+article.getId()+"#tags", tag);
            jedis.sadd("tags#"+tag+"#artigos", String.valueOf(article.getId()));
        }

        jedis.sadd("all#artigo", String.valueOf(article.getId()));
    }

    public Article find(int id){
        Jedis jedis = new Jedis();

        Map<String, String> fields = jedis.hgetAll("artigo#"+id);

        Article article = new Article();

        article.setId(id);
        article.setName(fields.get("name"));
        article.setFile(fields.get("file"));
        article.setDesc(fields.get("desc"));
        article.setData(fields.get("data"));

        Set<String> tags = jedis.smembers("artigo#"+id+"#tags");
        article.setTags(tags.stream().toList());

        return article;
    }
    public List<String> tags(int artigoId){
        Article a = find(artigoId);
        return a.getTags();
    }
    public List<Article> findAll(){
        Jedis jedis = new Jedis();
        Set<String> ids = jedis.smembers("all#artigo");
        List<Article> articles = new ArrayList<>();
        for (String id: ids) {
            articles.add(find(Integer.parseInt(id)));
        }
        return articles;
    }

    public List<Article> findByTag(String tag){
        Jedis jedis = new Jedis();

        Set<String> ids = jedis.smembers("tags#"+tag+"#artigos");
        List<Article> articles = new ArrayList<>();
        for (String id: ids) {
            articles.add(find(Integer.parseInt(id)));
        }
        return articles;
    }
}
