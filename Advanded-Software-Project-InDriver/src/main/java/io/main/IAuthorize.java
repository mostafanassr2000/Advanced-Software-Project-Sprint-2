package io.main;

public interface IAuthorize {

	public ApplicationUser login(String un, String pw);
	public boolean register(ApplicationUser AU);
}
