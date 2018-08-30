
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="example")
@Data
class Example {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer id;
	@Column(name="nombre", length=40)
	String name;
	String addressStreet;
	String addressNumber;
	String addressOther;
	
	
}
