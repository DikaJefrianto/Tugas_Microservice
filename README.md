# UAS Arsitektur Berbasis Layanan  
## Implementasi Arsitektur Microservices pada Sistem Perpustakaan

**Nama** : Dika Jefrianto  
**Kelas** : TRPL 3D  
**Mata Kuliah** : Arsitektur Berbasis Layanan  

---

## 1. Pendahuluan

Proyek ini merupakan implementasi **arsitektur microservices** pada sistem **Perpustakaan** dengan menerapkan pendekatan **Command Query Responsibility Segregation (CQRS)** dan **Event-Driven Architecture**.  
Sistem dikembangkan menggunakan **Spring Boot**, dideploy ke **Kubernetes**, serta dilengkapi dengan **CI/CD Pipeline**, **Logging terpusat**, dan **Monitoring sistem** untuk memenuhi prinsip aplikasi modern yang **scalable**, **reliable**, dan **maintainable**.

---

## 2. Teknologi yang Digunakan

| Kategori | Teknologi |
|--------|----------|
| Backend | Spring Boot, Java 17 |
| Message Broker | RabbitMQ |
| Database Command | MySQL |
| Database Query | MongoDB |
| Service Discovery | Eureka Server |
| API Management | API Gateway |
| Container & Orchestration | Docker, Kubernetes |
| Logging | ELK Stack (Elasticsearch, Logstash, Kibana) |
| Monitoring | Prometheus, Grafana |
| CI/CD | Jenkins |

---

## 3. Arsitektur Sistem

### 3.1 Arsitektur Microservices

Sistem dibangun menggunakan beberapa microservice independen sebagai berikut:
1. **Service Anggota**
2. **Service Buku**
3. **Service Peminjaman**
4. **Service Pengembalian** (menangani proses bisnis pengembalian)
5. **API Gateway**
6. **Eureka Server**

Setiap service memiliki database sendiri sesuai prinsip **Database per Service**.

---

### 3.2 Penerapan CQRS

Arsitektur CQRS diterapkan dengan pemisahan sebagai berikut:

- **Command Side (Write)**
  - Menggunakan **MySQL**
  - Mengelola operasi Create, Update, dan Delete
- **Query Side (Read)**
  - Menggunakan **MongoDB**
  - Mengelola operasi Read

Sinkronisasi data antara Command dan Query dilakukan secara **asynchronous** menggunakan **RabbitMQ**.

---

### 3.3 Event-Driven Communication

Komunikasi antar service menggunakan pendekatan **event-driven**, di mana:
- Setiap perubahan data pada Command Side akan menghasilkan event
- Event dikirim ke RabbitMQ
- Service terkait akan memproses event sesuai kebutuhan masing-masing

Pendekatan ini mengurangi **tight coupling** antar service.

---

## 4. Implementasi Logging dan Monitoring

### 4.1 Logging (ELK Stack)

Sistem logging terpusat menggunakan:
- **Logstash** sebagai log collector
- **Elasticsearch** sebagai penyimpanan log
- **Kibana** sebagai visualisasi log

Aplikasi mengirim log melalui **TCP Appender** dengan konfigurasi tambahan pada `logback-spring.xml` untuk mempermudah proses filtering berdasarkan nama service.

---

### 4.2 Monitoring (Prometheus dan Grafana)

Monitoring sistem dilakukan dengan:
- **Spring Boot Actuator** sebagai penyedia metrik
- **Prometheus** sebagai pengambil data metrik
- **Grafana** sebagai media visualisasi

Dashboard Grafana digunakan untuk memantau:
- Penggunaan CPU dan Memory
- Status service
- Performa aplikasi

---

## 5. Deployment Menggunakan Kubernetes

### 5.1 Konfigurasi Kubernetes

Deployment sistem dilakukan menggunakan file **YAML Manifest**, yang meliputi:
- **Namespace** untuk isolasi lingkungan
- **Secrets** untuk menyimpan konfigurasi sensitif
- **Deployment dan Service** untuk setiap microservice

---

### 5.2 Optimasi Resource

Untuk menyesuaikan dengan keterbatasan resource lokal, setiap pod dikonfigurasi dengan:
- Batasan memory
- Pengaturan JVM heap size

Pendekatan ini bertujuan untuk menjaga stabilitas cluster Kubernetes.

---

## 6. CI/CD Pipeline Menggunakan Jenkins

Pipeline CI/CD diterapkan menggunakan **Jenkins** dengan tahapan:
1. **Build Aplikasi** menggunakan Maven
2. **Build dan Push Docker Image** ke Docker Hub
3. **Deployment Otomatis** ke Kubernetes menggunakan `kubectl`

Pipeline ini memungkinkan proses pengembangan dan deployment dilakukan secara **otomatis dan konsisten**.

---

## 7. Alur Menjalankan Sistem

### 7.1 Menjalankan Infrastruktur Monitoring
- ELK Stack
- Prometheus
- Grafana

Dijalankan menggunakan **Docker Compose**.

### 7.2 Deployment Kubernetes
- Pembuatan Namespace dan Secrets
- Deployment database, message broker, dan service discovery
- Deployment seluruh microservice dan API Gateway

---

## 8. Akses Sistem

| Komponen | Alamat |
|-------|-------|
| API Gateway | http://localhost:30000/api |
| Kibana | http://localhost:5601 |
| Grafana | http://localhost:3000 |
| Eureka Server | http://localhost:8761 |
| phpMyAdmin | http://localhost:32000 |
| Mongo Express | http://localhost:8085 |

---

## 9. Kesimpulan

Implementasi sistem ini menunjukkan penerapan **arsitektur microservices** dengan pendekatan **CQRS dan Event-Driven Architecture** yang terintegrasi dengan:
- Kubernetes sebagai platform orkestrasi
- ELK Stack untuk logging terpusat
- Prometheus dan Grafana untuk monitoring
- Jenkins untuk otomatisasi CI/CD

Arsitektur ini mampu meningkatkan **skalabilitas**, **maintainability**, dan **reliability** sistem secara keseluruhan.

---
