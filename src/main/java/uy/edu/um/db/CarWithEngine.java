package uy.edu.um.db;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CAR_WITH_ENGINE")
public class CarWithEngine {
	@Id
	@Column(name="CAR_ID")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	private String name;
	private String color;
	@OneToOne (cascade= CascadeType.ALL)
	@JoinColumn(name="ENGINE_ID")
	private Engine engine;
}
