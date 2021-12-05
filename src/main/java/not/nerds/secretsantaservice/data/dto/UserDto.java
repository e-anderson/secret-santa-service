package not.nerds.secretsantaservice.data.dto;

import not.nerds.secretsantaservice.data.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDto {
        private int id;
        private String email;
        private Date birthDate;
        private List<ExchangeDto> exchangesHosted;
        private List<ExchangeDto> exchangesParticipatedIn;


        public UserDto(User user) {
            this.id=user.getId();
            this.email=user.getEmail();
            this.birthDate=user.getBirthDate();
            List<ExchangeDto> exchangesHosted = new ArrayList<ExchangeDto>();
            user.getExchangesHosted().forEach(eh -> {
                ExchangeDto exchange = new ExchangeDto(eh);
                exchangesHosted.add(exchange);
            });
            this.exchangesHosted = exchangesHosted;

            List<ExchangeDto> exchangesParticipatedIn = new ArrayList<ExchangeDto>();
            user.getExchangesHosted().forEach(eh -> {
                ExchangeDto exchange = new ExchangeDto(eh);
                exchangesParticipatedIn.add(exchange);
            });
            this.exchangesParticipatedIn = exchangesParticipatedIn;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        public List<ExchangeDto> getExchangesHosted() {
            return exchangesHosted;
        }

        public void setExchangesHosted(List<ExchangeDto> exchangesHosted) {
            this.exchangesHosted = exchangesHosted;
        }

        public List<ExchangeDto> getExchangesParticipatedIn() {
            return exchangesParticipatedIn;
        }

        public void setExchangesParticipatedIn(List<ExchangeDto> exchangesParticipatedIn) {
            this.exchangesParticipatedIn = exchangesParticipatedIn;
        }
    }


