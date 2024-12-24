package tech.yuri.agregadorinvestimentos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotNull(message = "O nome do usuário é obrigatório.")
        @Size(min = 4, message = "O nome do usuário deve obter pelo menos 3 caracteres.")
        String username,

        @NotNull(message = "Email é obrigatório")
        @Email(message = "Formato de e-mail inválido.")
        String email,

        @NotNull(message = "Senha é obrigatória")
        @Size(min=6, message = "A senha deve ter pelo menos 6 carateceres")
        String password

){}
