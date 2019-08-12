package afti.proto.circuitcourt;

public class Product {

    private String nom;
    private Double prix;
    private String tags;
    private String type;
    private int id;

    public Product(int id, String nom, Double prix, String tags,String type) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.tags = tags;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Double getPrix() {
        return prix;
    }

    public String getTags() {
        return tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
