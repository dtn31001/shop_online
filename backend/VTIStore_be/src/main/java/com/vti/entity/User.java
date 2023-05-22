package com.vti.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email"), @UniqueConstraint(columnNames = "phone") })
public class User {
	private static final long serialVersionUID = 1L;
	@Column(name = "user_id")
	@Id // khoa chinh
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
//	@Size(max = 20)
	@Column(name = "username")
	private String userName;

	@NotBlank
//	@Size(max = 50)
	@Column(name = "email")
	private String email;

	@NotBlank
//	@Size(max = 50)
	@Column(name = "password")
	private String password;

	
	@Column(name = "phone")
	private String phone;

	
	@Column(name = "adress")
	private String adress;

	@Lob
	@Column(name = "avatar")
	private String avatar;

	@Column(name = "wallet_cast")
	private double walletCast;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Cart> carts;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(@NotBlank String userName, @NotBlank String email, @NotBlank String password, double walletCast) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.walletCast = walletCast;
	}



	public User(@NotBlank String email, @NotBlank String password, String phone, String adress, String avatar,
			double walletCast, List<Cart> carts, Set<Role> roles) {
		super();
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.adress = adress;
		this.avatar = avatar;
		this.walletCast = walletCast;
		this.carts = carts;
		this.roles = roles;
	}



	
}
