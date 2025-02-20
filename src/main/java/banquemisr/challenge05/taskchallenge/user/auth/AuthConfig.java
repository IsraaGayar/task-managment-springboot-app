package banquemisr.challenge05.taskchallenge.user.auth;

//@Configuration
public class AuthConfig{
//    private final UserRepository userRepository;
//
//    public AuthConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Bean
//    UserDetailsService userDetailsService() {
//        return username -> userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//
//
//    @Bean
//    BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        CustomAuthProvider authProvider = new CustomAuthProvider();
//        authProvider.setUserDetailsService(userDetailsService()); // Your UserDetailsService bean
//        authProvider.setPasswordEncoder(passwordEncoder()); // Your PasswordEncoder bean!  Crucial!
//        return authProvider;
//    }
}
