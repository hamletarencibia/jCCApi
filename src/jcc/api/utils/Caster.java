package jcc.api.utils;

import java.util.LinkedList;

import javax.swing.DefaultListModel;
/**This class contains only static method. 
 * The methods contained in this class have the only purpose of convert from a sort classes to other sort of classes.
 * @author Hamlet Arencibia Casanova*/
public class Caster {
	/**Converts from LinkedList to DefaultListModel.
	 * @param <E> The parameter class of the LinkedList.
	 * @param vector The LinkedList to convert.
	 * @return Returns a DefaultListModel with the same values of the specified LinkedList.*/
	public static <E> DefaultListModel<E> defaultListModel(LinkedList<E> vector){
		if(vector!=null){
			DefaultListModel<E> model=new DefaultListModel<E>();
			for(int i=0;i<vector.size();i++){
				model.addElement(vector.get(i));
			}
			return model;
		}
		return null;
	}
	/**Converts from any class array to DefaultListModel.
	 * @param <E> The parameter class of the array.
	 * @param vector The class array to convert.
	 * @return Returns a DefaultListModel with the same values of the specified array.*/
	public static <E> DefaultListModel<E> defaultListModel(E[] vector){
		if(vector!=null){
			DefaultListModel<E> model=new DefaultListModel<E>();
			for(int i=0;i<vector.length;i++){
				model.addElement(vector[i]);
			}
			return model;
		}
		return null;
	}
	/**Converts from any class array to LinkedList.
	 * @param <E> The parameter class of the array.
	 * @param vector The class array to convert.
	 * @return Returns a LinkedList with the same values of the specified array.*/
	public static <E> LinkedList<E> linkedList(E[] vector){
		if(vector!=null){
			LinkedList<E> model=new LinkedList<E>();
			for(int i=0;i<vector.length;i++){
				model.add(vector[i]);
			}
			return model;
		}
		return null;
	}
	/**Converts from DefaultListModel to LinkedList.
	 * @param <E> The parameter class of the DefaultListModel.
	 * @param vector The DefaultListModel to convert.
	 * @return Returns a LinkedList with the same values of the specified DefaultListModel.*/
	public static <E> LinkedList<E> linkedList(DefaultListModel<E> vector){
		if(vector!=null){
			LinkedList<E> model=new LinkedList<E>();
			for(int i=0;i<vector.getSize();i++){
				model.add(vector.get(i));
			}
			return model;
		}
		return null;
	}
}
