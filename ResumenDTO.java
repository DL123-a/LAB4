import java.util.*;
public class ResumenDTO {
    private int totalCitas;
    private int totalPalabras;
    private java.util.List<ItemResumen> fragmentos;
    public ResumenDTO() { fragmentos = new java.util.ArrayList<>(); }
    public void setTotalCitas(int totalCitas) { this.totalCitas = totalCitas; }
    public void setTotalPalabras(int totalPalabras) { this.totalPalabras = totalPalabras; }
    public void addItem(ItemResumen item) { fragmentos.add(item); }
    public int getTotalCitas() { return totalCitas; }
    public int getTotalPalabras() { return totalPalabras; }
    public java.util.List<ItemResumen> getFragmentos() { return fragmentos; }
}
