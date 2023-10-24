package upc.edu.pe.softroute.trackingstatus.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "package")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tracking_number", nullable = false)
    private Integer trackingNumber;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    public PackageEntity(Integer trackingNumber, String status) {
        this.trackingNumber = trackingNumber;
        this.status = status;
    }
}
