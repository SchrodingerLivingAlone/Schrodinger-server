package com.shrodinger.domain.user.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import com.shrodinger.domain.acoountbook.entity.AccountBook;
import com.shrodinger.domain.neighborhood.neighborhood.entity.Neighborhood;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@Builder
@Setter
@NoArgsConstructor
@Entity
@Table(name = "member")
@AllArgsConstructor
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String email; // 이메일
    @Column
    private String password; // 비밀번호
    @Column
    private String nickname; // 닉네임

    @Column(name = "profile_image")
    private String profileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighborhood_id") // name should match the referencedColumnName in @ManyToOne
    private Neighborhood neighborhood;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<AccountBook> accountBooks = new ArrayList<>();


    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }


}
