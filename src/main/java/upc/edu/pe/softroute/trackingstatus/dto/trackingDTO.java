package upc.edu.pe.softroute.trackingstatus.dto;

public class trackingDTO {

    private Long id;
    private Integer trackingNumber;
    private String status;


    // Constructor vacío
    // public Package() {
    //     return 
    // }

    // Constructor con parámetros
    public void Package(Integer trackingNumber, String status) {
        this.trackingNumber = trackingNumber;
        this.status = status;
    }

    // Getter y Setter para trackingNumber
    public Integer getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Integer trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    // Getter y Setter para status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public trackingDTO(Integer trackingNumber, String status) {
        this.trackingNumber = trackingNumber;
        this.status = status;
    }
    
}
