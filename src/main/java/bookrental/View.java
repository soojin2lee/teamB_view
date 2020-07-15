package bookrental;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="View_table")
public class View {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long rentalId;
        private Long bookId;
        private Long useId;
        private String bookName;
        private Date regDate;
        private Integer rentalFee;
        private String rentalStaus;
        private String userName;
        private Date returnDate;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getRentalId() {
            return rentalId;
        }

        public void setRentalId(Long rentalId) {
            this.rentalId = rentalId;
        }
        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }
        public Long getUseId() {
            return useId;
        }

        public void setUseId(Long useId) {
            this.useId = useId;
        }
        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }
        public Date getRegDate() {
            return regDate;
        }

        public void setRegDate(Date regDate) {
            this.regDate = regDate;
        }
        public Integer getRentalFee() {
            return rentalFee;
        }

        public void setRentalFee(Integer rentalFee) {
            this.rentalFee = rentalFee;
        }
        public String getRentalStaus() {
            return rentalStaus;
        }

        public void setRentalStaus(String rentalStaus) {
            this.rentalStaus = rentalStaus;
        }
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
        public Date getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
        }

}
