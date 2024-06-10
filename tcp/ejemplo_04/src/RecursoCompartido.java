import java.io.Serializable;

public class RecursoCompartido implements Serializable {
    private String data;
    
    public RecursoCompartido(String data) {
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String toString() {
        return this.data;
    }
}
