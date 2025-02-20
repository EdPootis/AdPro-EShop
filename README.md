# Advanced Programming E-Shop
**Nama: Edmond Christian**<br>
**NPM: 2306208363**

<details>
<summary>Modul 1</summary>

## Modul 1
### Reflection 1

Melalui modul ini saya telah mempelajari mengenai *coding standard* seperti clean code. Salah satu *practice*nya yang sederhana adalah memberikan nama yang berarti pada nama variabel, fungsi, ataupun *class*. Dengan *meaningful names* saya tidak perlu sering mengecek maksud dari variabel/fungsi tersebut sehingga menghemat waktu. Pada modul semua nama-nama memiliki maksud tertentu yang jelas dan ringkas juga. Menurut saya, penamaan variabel saya sudah cukup baik karena sudah saya biasakan sejak dulu.
<br>Prinsip *clean code* yang kedua adalah mengenai pembuatan fungsi yang sederhana dan melakukan 1 hal tanpa mengubah hal lain. Biasanya fungsi yang saya buat kebalikannya, atau mencoba melakukan suatu fitur secara keseluruhan dalam satu fungsi. Setelah melihat tutorial, saya menjadi sadar akan fungsi yang lebih rapih dan sederhana, yang kemudian digunakan bersama untuk sebuah fitur.
<br>Sebelumnya, saya mengira bahwa *comment* yang lengkap berarti bagus. Namun setelah melihat fungsi-fungsi yang sederhana dan dapat menjelaskan dirinya hanya dengan dilihat, saya jadi tahu bahwa *comment* tidak selalunya dibutuhkan. Melainkan ada saat-saat tertentu dimana *comment* diperlukan (sesuai dengan modul). Meskipun begitu, pada tutorial ini saya merasa ada bagian yang kurang jelas, contohnya bagian tes yang menurut saya memerlukan *comment* lebih lanjut.
<br>*Practice clean code* yang tersisa adalah data struktur dan objek, dan *error handling*. Biasanya dalam membuat *class* suatu objek saya selalu menggunakan *concrete class* karena tidak merasa membutuhkan *interface* atau *abstract class*, tapi dengan berkembangnya kompleksitas proyek yang dikerjakan saya mungkin akan semakin banyak menggunakannya. Hal yang sama juga berlaku untuk *error handling*, yang saya lakukan biasanya minimal dan tidak mengikuti standar yang berlaku. Contohnya, pada tutorial ini *error handling* yang saya terapkan hanya pada di tes-tes.
<br><br>
Pada tutorial 1 ini, saya belum menerapkan standar *secure coding* apapun. Ini adalah karena tidak adanya *user* sehingga autentikasi dan otorisasi tidak dapat dilakukan. Namun ada juga *secure coding* yang dapat saya lakukan tapi tidak lakukan, yaitu *input data validation* dan *output data encoding*.
<br><br>
Hal-hal yang perlu saya perbaiki adalah
- Membuat *coding syntax* yang lebih jelas sehingga kebutuhan untuk *comment* berkurang
- Memperbaiki kebiasaan buruk dalam membuat fungsi
- Mengimplementasi penggunaan data struktur dan objek yang lebih baik
- Mengimplementasi *error handling*


Lalu, ada juga beberapa kekurangan spesifik pada *source code* proyek ini, yaitu
- Tidak adanya *input data validation* dan *output data encoding* sama sekali
- Tidak adanya sistem untuk melakukan autentikasi dan otorisasi
- Sintaks kode yang membutuhkan *comment*
- Penyimpanan data/*repository* yang berdasarkan *list* java
- Tampilan aplikasi yang polos

### Reflection 2
1.
- Setelah membuat *unit tests* saya merasa lebih yakin bahwa aplikasi yang dibuat akan berjalan sesuai dengan fungsinya.
- Menurut saya, jumlah *unit test* dalam suatu *class* dipengaruhi oleh berapa banyak fungsi atau hal yang dilakukan di dalamnya, dan hasil yang diinginkan. Namun *unit test* sebaiknya dilakukan dalam jumlah yang sedikit tapi memiliki *coverage* yang banyak.
- Kita mengetahui *unit test* cukup jika memiliki *coverage* yang banyak dan juga mencakupi *edge cases*. Dengan ini kita mengurangi kemungkinan kasus di mana program error.
- Jika kita memiliki 100% *code coverage* bukan berarti program tidak memiliki *bugs* atau error sama sekali. *Code coverage* hanya berarti berapa bagian dari kode yang sudah dijalankan pada suatu tes, jika tes yang dijalankan kurang lengkap atau tidak efektif maka tidak menutupi kemungkinan munculnya *bug* atau error.

2. Menurut saya pembuatan file baru untuk melakukan tes yang memverifikasi jumlah barang dalam *list product* akan mengurangi kualitas kode *functional test*. Ini karena banyaknya prosedur dan variabel yang mirip. Karena itu, sebaiknya tes tersebut dilakukan di *class* yang sama seperti pada file `CreateProductFunctionalTest.java`. Jika tidak, kualitas kode akan berkurang karena adanya pengulangan/duplikasi kode. Selain itu verifikasi jumlah barang dalam *list product* juga masih berhubungan dengan proses *create product* yang sudah memiliki filenya sehingga lebih masuk akan jika berada pada file yang sama.
</details>

<details open>

<summary>Modul 2</summary>

## Modul 2
### Reflection
1. Pada *exercise* saya memperbaiki beberapa isu kode, isu-isunya adalah sebagai berikut:
- `Add a nested comment explaining why this method is empty, throw an UnsupportedOperationException or complete the implementation.
Methods should not be empty java:S1186`, isu ini disebabkan adanya *method* pada *concrete class* yang memiliki *body* kosong. Pada kode saya hal ini terdapat pada *method* `SetUp()` dalam salah satu *unit test*. Hal yang saya lakukan untuk memperbaiki isunya adalah menambahkan *comment* yang menjelaskan kenapa terdapat *method* kosong tersebut.
- `Group dependencies by their destination.
Dependencies should be grouped by destination kotlin:S6629`, isu ini terjadi karena pada `build.gradle.kts` urutan *dependencies* yang ditulis tidak dikelompokkan berdasarkan tujuannya dan tidak berurutan. Untuk memperbaikinya, saya hanya perlu mengelompokkan *depedencies* sesuai tujuan/fungsinya (misal: `implementation(..)` dan `testImplementation(..)`)
- `'Unnecessary imports should be removed java:S1128`, sesuai namanya isu ini terjadi karena terdapat *import* yang tidak digunakan dalam beberapa file. Cara memperbaikinya hanya dengan menghapusnya dari kode.

- `Swap these 2 arguments so they are in the correct order: expected value, actual value.
Assertion arguments should be passed in the correct order java:S3415`, isu ini disebabkan urutan argumen pada *assertion method* yang kebalik. Hal ini dikarenakan urutan untuk *expected value* dan *actual value* sudah ditentukan untuk meningkatkan *maintability* kode. Yang saya lakukan untuk memperbaikinya adalah mengubah urutan argumen pada *method-method* tersebut.

- Isu terakhir yang saya perbaiki adalah menghapus modifier `Public` pada beberapa *java test class*.

2. Menurut saya, implementasi yang sekarang telah memenuhi definisi CI dan CD. Pada implementasi sekarang, setiap terjadi perubahan atau perbaruan *codebase* yang di-*push*, maka akan langsung dilakukan pengetesan *unit tests* oleh GitHub Actions, dan *code analysis* oleh OSSF Scorecard dan SonarCloud sehingga sudah memenuhi definisi dari *Continuous Integration*. Dengan ini kode akan dicek kualitas dan kebenarannya setiap terjadi perubahan. Lalu, setiap *push* pada branch *master* yang dilakukan juga akan dilakukan *deployment* yang otomatis oleh Koyeb beserta tes-tesnya sehingga sudah memenuhi *Continuous Deployment*. Dengan ini maka workflow kode sudah memenuhi definisi dari *Continuous Integration* dan *Continuous Deployment*.
</details>


