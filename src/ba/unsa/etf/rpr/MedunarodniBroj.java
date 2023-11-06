package ba.unsa.etf.rpr;
import java.util.Objects;
public class MedunarodniBroj extends TelefonskiBroj{
    private String drzava;
    private String broj;
    public MedunarodniBroj(String drzava, String broj){
        this.drzava = drzava;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        if(drzava == null || broj == null)return null;
        return drzava+ "/" +broj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(drzava, broj);
    }

    public String getDrzava() {
        return drzava;
    }

    public String getBroj() {
        return broj;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }
}
