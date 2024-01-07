package com.example.db.account;

import com.example.db.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@Table(name = "account")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AccountEntity extends BaseEntity {
}
