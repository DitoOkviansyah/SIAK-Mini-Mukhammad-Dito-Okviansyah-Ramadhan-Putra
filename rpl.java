import java.util.ArrayList;
import java.util.List;

// ----------------------------
// Class Mahasiswa
// ----------------------------
class Mahasiswa {
    private String nim;
    private String nama;
    private Dosen dosenPembimbing;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    // Getter
    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public Dosen getDosenPembimbing() { return dosenPembimbing; }

    // Setter
    public void setDosenPembimbing(Dosen dosen) {
        this.dosenPembimbing = dosen;
    }

    // US-002: Lihat detail dosen pembimbing
    public String lihatDosenPembimbing() {
        if (dosenPembimbing != null) {
            return "Dosen Pembimbing: " + dosenPembimbing.getNama() +
                   " (NIP: " + dosenPembimbing.getNip() + ")";
        } else {
            return "Belum memiliki dosen pembimbing.";
        }
    }

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "nim='" + nim + '\'' +
                ", nama='" + nama + '\'' +
                '}';
    }
}

// ----------------------------
// Class Dosen
// ----------------------------
class Dosen {
    private String nip;
    private String nama;
    private List<Mahasiswa> mahasiswaBimbingan;

    public Dosen(String nip, String nama) {
        this.nip = nip;
        this.nama = nama;
        this.mahasiswaBimbingan = new ArrayList<>();
    }

    // Getter
    public String getNip() { return nip; }
    public String getNama() { return nama; }
    public List<Mahasiswa> getMahasiswaBimbingan() {
        return new ArrayList<>(mahasiswaBimbingan); // Return copy untuk encapsulation
    }

    // US-001: Tambah mahasiswa ke bimbingan
    public void tambahMahasiswaBimbingan(Mahasiswa mhs) {
        if (mhs != null && !mahasiswaBimbingan.contains(mhs)) {
            mahasiswaBimbingan.add(mhs);
            mhs.setDosenPembimbing(this); // Update relasi dua arah
        }
    }

    // US-003: Tampilkan daftar mahasiswa bimbingan
    public String tampilkanDaftarBimbingan() {
        if (mahasiswaBimbingan.isEmpty()) {
            return "Tidak ada mahasiswa bimbingan.";
        }

        StringBuilder sb = new StringBuilder("Daftar Mahasiswa Bimbingan " + nama + ":\n");
        for (Mahasiswa m : mahasiswaBimbingan) {
            sb.append("- ").append(m.getNama()).append(" (NIM: ").append(m.getNim()).append(")\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String toString() {
        return "Dosen{" +
                "nip='" + nip + '\'' +
                ", nama='" + nama + '\'' +
                '}';
    }
}

// ----------------------------
// Demo Utama
// ----------------------------
public class rpl {
    public static void main(String[] args) {
        System.out.println("=== 🎓 Sistem Informasi Akademik Mini ===\n");

        // Buat objek dosen dan mahasiswa
        Dosen dosen = new Dosen("D1001", "Dr. Andi Pratama");
        Mahasiswa mhs1 = new Mahasiswa("M2001", "Rina Sari");
        Mahasiswa mhs2 = new Mahasiswa("M2002", "Budi Santoso");

        // --- US-001: Dosen menambahkan mahasiswa ke bimbingan ---
        System.out.println("🔹 US-001: Dosen menambahkan mahasiswa ke bimbingan");
        dosen.tambahMahasiswaBimbingan(mhs1);
        dosen.tambahMahasiswaBimbingan(mhs2);
        System.out.println("✅ Mahasiswa berhasil ditambahkan ke bimbingan.\n");

        // --- US-003: Dosen melihat daftar mahasiswa bimbingan ---
        System.out.println("🔹 US-003: Dosen melihat daftar mahasiswa bimbingan");
        System.out.println(dosen.tampilkanDaftarBimbingan());
        System.out.println();

        // --- US-002: Mahasiswa melihat detail dosen pembimbing ---
        System.out.println("🔹 US-002: Mahasiswa melihat dosen pembimbing");
        System.out.println(mhs1.getNama() + ": " + mhs1.lihatDosenPembimbing());
        System.out.println(mhs2.getNama() + ": " + mhs2.lihatDosenPembimbing());
        System.out.println();

        // Validasi tambahan: cek relasi dua arah
        System.out.println("🔹 Validasi Relasi Dua Arah:");
        System.out.println("Dosen dari " + mhs1.getNama() + ": " + mhs1.getDosenPembimbing().getNama());
        System.out.println("Jumlah bimbingan Dr. Andi: " + dosen.getMahasiswaBimbingan().size());
    }
}