package br.com.staroski.profanator;

final class Selection {

	public final int start;
	public final int end;
	public final boolean empty;

	public Selection() {
		this(0, 0);
	}

	public Selection(int start, int end) {
		this.start = Math.min(start, end);
		this.end = Math.max(start, end);
		this.empty = start == end;
	}
}
