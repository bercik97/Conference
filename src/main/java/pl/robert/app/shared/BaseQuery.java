package pl.robert.app.shared;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseQuery {

    @Id
    Long id;

    String uuid;

    public BaseQuery(Long id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object that) {
        return this == that || that instanceof BaseQuery
                    && Objects.equals(uuid, ((BaseQuery) that).uuid);
    }
}
