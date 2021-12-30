package io.main;

public interface IAuthorize {
	public ApplicationUser login(String un, String pw, IPersistence persistence);
	public boolean register(ApplicationUser AU, IPersistence persistence);
}
