package spittr.data;

import java.util.List;

import spittle.Spittle;

public interface SpittleRepository {
	List<Spittle> findSpittles(long max, int count);
}
