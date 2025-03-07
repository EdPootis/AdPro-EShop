# Advanced Programming E-Shop
**Nama: Edmond Christian**<br>
**NPM: 2306208363**

[Link Deployment](https://frequent-lynnett-edpootis-3043dec4.koyeb.app/)

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

<details>

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

2. Menurut saya, implementasi yang sekarang telah memenuhi definisi CI dan CD. Pada implementasi sekarang, setiap terjadi perubahan atau perbaruan *codebase* yang di-*push*, maka akan langsung dilakukan pengetesan *unit tests* oleh GitHub Actions, dan *code analysis* oleh OSSF Scorecard dan SonarCloud sehingga sudah memenuhi definisi dari *Continuous Integration*. Dengan ini kode akan dicek kualitas dan kebenarannya setiap terjadi perubahan. Lalu, setiap *push* pada branch *master* yang dilakukan juga akan dilakukan *deployment* yang otomatis oleh Koyeb beserta tes-tesnya sehingga sudah memenuhi *Continuous Deployment*. Dengan ini maka workflow kode sudah memenuhi definisi dari *Continuous Integration* dan *Continuous Deployment*. Dengan ini juga setiap perubahan kode di *branch master* akan langsung mengetes dan mengdeploy proyek sehingga proses automasi CI/CD terjadi.
</details>

<details>

<summary>Modul 3</summary>

## Modul 3
### Reflection

1. Explain what principles you apply to your project!
- Single Responsibility Principle, pada proyek ini, *principle* ini diterapkan dengan memindahkan class `CarController` yang awalnya berada pada file `ProductController.java` ke filenya sendiri. Hal ini dilakukan karena keduanya memiliki tujuan yang berbeda.
- Open Closed Principle, pada proyek ini, OCP diterapkan pada interface `CarService` dan `ProductService`. Jika ingin menambahkan fitur baru terkait model yang ada, dapat dibuat implementasi yang baru tanpa melakukan modifikasi terhadap interface ataupun implementasi yang sudah ada.
- Liskov Substitution Principle, pada proyek ini awalnya class `CarController` memiliki *inheritance* dari `ProductController` sehingga menjadi subclassnya. Agar LSP diterapkan, maka dihapus hubungan *inheritance* dari kedua kelas tersebut. Ini dilakukan karena menurut LSP superclass `ProductController` harus bisa disubstitusi dengan `CarController`, tapi kedua class tersebut memiliki fungsi yang berbeda sehingga tidak bisa, dan sebaiknya dihapus hubungan super dan subclassnya.
- Interface Segregation Principle, pada proyek ini diterapkan melalui interface `CarService` dan `ProductService` yang hanya berisi *method* yang diperlukan implementasinya, tanpa mengandung *method* yang tidak diperlukan (sudah tersegregasi menurut modelnya.
- Dependency Inversion Principle, pada proyek ini awalnya class `CarController` yang merupakan *high level module* memiliki dependensi terhadap `CarServiceImpl` yang merupakan sebuah *detailed*/*concrete* class, sehingga agar DIP diterapkan digganti dependensi tersebut terhadap interface `CarService`.
2. Explain the advantages of applying SOLID principles to your project with examples.
- Meningkatkan *maintainability*, contohnya karena SOLID Principle, setiap *model*, *service*, *repository*, dan *controller* tersegregasi berdasarkan *model* `Product` atau `Car`. Dengan ini maka jika ingin mengubah fitur-fitur yang berkaitan dengan salah satu model, hanya perlu mengubah kode dari file yang terkaitan, tanpa memengaruhi kode untuk model lain.
- Memudahkan *testing* dan pengembangan kode, karena setiap class yang sudah dipisahkan dengan tujuannya masing-masing, menjadi mudah untuk mengecek bagian apa yang sudah dites atau belum. Juga karena itu untuk membuat fitur atau pengembangan proyek menjadi lebih cepat dan mudah karena struktur *codebase* yang rapih dapat mengurangi *bug* yang dapat terbuat, dan kemudahan membaca kode juga berpengaruh.

3. Explain the disadvantages of not applying SOLID principles to your project with examples.
- Kode yang berantakan/sulit dibaca, misalkan SOLID Principle tidak diterapkan maka class `CarController` akan tetap berada pada file `ProductController.java` yang seharusnya hanya untuk class `ProductController`, akibatnya kode berantakan yang juga memengaruhi hal lainnya.
- Mengurangi *maintainability*, misalkan SOLID Principle tidak diterapkan maka suatu interface bisa saja menjadi `ProductAndCarService`
</details>

<details open>

<summary>Modul 4</summary>

## Modul 4
### Reflection

1. Menurut saya pembuatan *flow* TDD pada modul ini berguna. Percival mengatakan bahwa evaluasi tes-tes dilakukan berdasarkan *correctness*,  *maintanability*, dan *productive workflow*.
- *Correctness*: Pada pengerjaan modul ini, dengan dibikinnya tes-tes terlebih dahulu, setiap *method* dari class sudah ditentukan apa yang seharusnya dilakukan/benar. Sehingga TDD membantu memastikan *correctness* kode.
- *Maintanability*: Dengan melakukan TDD, saya menjadi lebih yakin untuk melakukan *refactoring* karena setelahnya saya bisa menjalankan tes-tes yang sudah dibuat untuk mengecek apakah refactor berhasil dilakukan atau tidak. Contohnya adalah saat melakukan *refactor* mengganti *string literal* menjadi sebuah elemen *enumeration*, setelah dilakukan selama tes berhasil berarti *refactor* tidak merusak fungsionalitas kode.
- *Productive Workflow*: Walaupun *TDD based development* saya tidak merasakan lama dalam menunggu tes, sebaliknya untuk setiap fitur/*method* yang dibuat saya bisa langsung mengeceknya dengan menjalankan tes yang spesifik terhadap fitur tersebut. Sehingga tidak harus menyelesaikan keseluruhan kode untuk menjalankan seluruh *test suite*, melainkan bisa berupa *subset*-nya.

2. Menurut saya, tes-tes saya sudah mengikuti prinsip F.I.R.S.T.
- *Fast*: Tes-tes yang ada dapat berjalan dengan cepat sehingga saya tidak merasa terganggu dalam melakukannya. Jika tes-tes memerlukan komponen eksternal, maka dapat dilakukan *mock* sehingga waktu tes tidak terefek banyak.
- *Isolated/Independent*: Tes yang saya buat tidak memengaruhi ataupun dipengaruhi tes-tes lain, ini karena digunakan metode seperti `setUp()` yang dijalankan sebelum setiap tesnya sehingga kondisi awal tes sama semua/konsisten.
- *Repeatable*: Tes yang dibuat *repeatable* karena hasilnya sama jika kode tidak ada yang diubah. Ini juga berhubungan dengan metode `setUp()` yang menghasilkan hasil tes yang sama setiap kali dijalankan.
- *Self-validating*: Pada *test suite* proyek ini, semuanya hanya melakukan *assertion* sehingga tidak ada proses yang memerlukan pengecekan manual.
- *Thorough/Timely*: Walaupun setelah dicek tidak terdapat 100% *code coverage*, *test suite*-nya menurut saya sudah mengcover kebanyakan case-case.

</details>
