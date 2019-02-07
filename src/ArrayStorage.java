import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;
    private static final String OBJECT_DOESNT_EXIST = "This object doesn't exist in storage";
    private static final String OBJECT_EXISTS = "This object already exists";

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        Objects.requireNonNull(r);
        if (findIndex(r.getUuid()) >= 0) {
            System.err.println(OBJECT_EXISTS);
        } else {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            }
        }
    }

    public void update(Resume r) {
        Objects.requireNonNull(r);
        int index = findIndex(r.getUuid());
        if (index == -1) {
            System.err.println(OBJECT_DOESNT_EXIST);
        } else {
            storage[index] = r;
        }
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public Resume get(String uuid) {
        Objects.requireNonNull(uuid);
        int index = findIndex(uuid);
        if (index == -1) {
            System.err.println(OBJECT_DOESNT_EXIST);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        Objects.requireNonNull(uuid);
        int index = findIndex(uuid);
        if (index == -1) {
            System.err.println(OBJECT_DOESNT_EXIST);
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        return Arrays.copyOf(storage, size);
        //System.arraycopy(storage, 0, resumes, 0, size);
    }

    int size() {
        return size;
    }
}
