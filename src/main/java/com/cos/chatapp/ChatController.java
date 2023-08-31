package com.cos.chatapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

// SSE 프로토콜
@RequiredArgsConstructor
@RestController
public class ChatController {
    private final ChatRepository chatRepository;


    // 일대일 채팅/귓속말
    @CrossOrigin
    @GetMapping(value="/sender/{sender}/receiver/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMsg(@PathVariable String sender, @PathVariable String receiver) {
        return chatRepository.mFindBySender(sender,receiver)
                .subscribeOn(Schedulers.boundedElastic());
    }

    // Mono - 데이터를 한 번만 리턴받는다. 데이터 확인 차 삽입
    @CrossOrigin
    @PostMapping("/chat")
    public Mono<Chat> setMsg(@RequestBody Chat chat) {
        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat);
        // Object를 리턴하게 되면 자동으로 JSON으로 변환 -> MessageConverter
    }

    // 채팅방
    @CrossOrigin
    @GetMapping(value="/chat/roomNum/{roomNum}")
    public Flux<Chat> findByRoomNum(@PathVariable Integer roomNum) {
        return chatRepository.mFindByRoomNum(roomNum)
                .subscribeOn(Schedulers.boundedElastic());
                // 생명주기가 긴 태스크들에 적합, 요청할 때마다 제한 없이 스레드를 생성하지만 스레드 수를 제한한다.
    }
}
