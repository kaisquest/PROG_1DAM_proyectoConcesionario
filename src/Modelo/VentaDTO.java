package Modelo;

import java.util.Date;

public class VentaDTO {
    private int idVenta;
    private ClienteDTO cliente;
    private CocheDTO coche;
    private Date fecha;
    private float precioVenta;

    public VentaDTO(int idVenta, ClienteDTO cliente, CocheDTO coche, Date fecha, float precioVenta) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.coche = coche;
        this.fecha = fecha;
        this.precioVenta = precioVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public CocheDTO getCoche() {
        return coche;
    }

    public void setCoche(CocheDTO coche) {
        this.coche = coche;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
}
