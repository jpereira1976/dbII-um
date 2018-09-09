package uy.edu.um.db;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="SHOWROOM_LIST_ANN")
@Table(name="SHOWROM_LIST_ANN")
public class Showroom {
	@Id
	@Column(name="SHOWROOM_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	String manager;
	String location;
	@OneToMany
	@JoinTable
	(name="SHOWROOM_CAR_SET_ANN_JOINTABLE",
	joinColumns = @JoinColumn(name="SHOWROOM_ID")
	)
	@Cascade(CascadeType.ALL)
	List<Car> cars;
}
