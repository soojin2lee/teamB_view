package bookrental;

import bookrental.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ViewViewHandler {


    @Autowired
    private ViewRepository viewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookRegistered_then_CREATE_ (@Payload BookRegistered bookRegistered) {
        try {
            if (bookRegistered.isMe()) {
                // view 객체 생성
                View view = new View();
                // view 객체에 이벤트의 Value 를 set 함
                view.setBookId(bookRegistered.getId());
                view.setBookName(bookRegistered.getBookName());
                view.setRentalFee(bookRegistered.getRentalFee());
                // view 레파지 토리에 save
                viewRepository.save(view);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenRented_then_CREATE_ (@Payload Rented rented) {
        try {
            if (rented.isMe()) {
                // view 객체 생성
                View view = new View();
                // view 객체에 이벤트의 Value 를 set 함
                view.setRentalId(rented.getId());
                view.setBookId(rented.getBookId());
                view.setRegDate(rented.getRentalDate());
                view.setRentalFee(rented.getRentalFee());
                view.setRentalStaus("RENTED");
                // view 레파지 토리에 save
                viewRepository.save(view);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserRegistered_then_CREATE_ (@Payload UserRegistered userRegistered) {
        try {
            if (userRegistered.isMe()) {
                // view 객체 생성
                View view = new View();
                // view 객체에 이벤트의 Value 를 set 함
                view.setUseId(userRegistered.getId());
                view.setUserName(userRegistered.getUserName());
                // view 레파지 토리에 save
                viewRepository.save(view);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenReturned_then_UPDATE_(@Payload Returned returned) {
        System.out.println("##### whenReturned_then_UPDATE_!! Id: " + returned.getId());
        try {
            if (returned.isMe()) {
                // view 객체 조회
                List<View> viewList = viewRepository.findByRentalId(returned.getId());
                for(View view : viewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    view.setRentalStaus("RETURNED");
                    view.setReturnDate(returned.getReturnDate());
                    // view 레파지 토리에 save
                    viewRepository.save(view);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCancelled_then_UPDATE_(@Payload Cancelled cancelled) {

        System.out.println("##### whenCancelled_then_UPDATE_!! Id: " + cancelled.getId());
        try {
            if (cancelled.isMe()) {
                // view 객체 조회
                List<View> viewList = viewRepository.findByRentalId(cancelled.getId());
                for(View view : viewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    view.setRentalStaus("CANCELLED");
                    view.setReturnDate(cancelled.getReturnDate());
                    // view 레파지 토리에 save
                    viewRepository.save(view);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
        @StreamListener(KafkaProcessor.INPUT)
    public void whenCancelled_then_DELETE_(@Payload Cancelled cancelled) {

        System.out.println("##### whenCancelled_then_DELETE_!! Id: " + cancelled.getId());
        try {
            if (cancelled.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                viewRepository.deleteByRentalId(cancelled.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
     */
}