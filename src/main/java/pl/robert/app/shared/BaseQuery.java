package pl.robert.app.shared;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

import javax.persistence.MappedSuperclass;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseQuery {

    String uuid;

    public BaseQuery(String uuid) {
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
