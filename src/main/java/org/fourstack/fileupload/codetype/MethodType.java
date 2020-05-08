package org.fourstack.fileupload.codetype;

/**
 * Enum <b><i>MethodType</i></b> is used to define different types of Http
 * Methods.
 * 
 * @author Manjunath_HM
 *
 */
public enum MethodType {

	GET {
		@Override
		public String toString() {
			return "@GET";
		}
	},

	POST {
		@Override
		public String toString() {
			return "@POST";
		}
	},

	PUT {
		@Override
		public String toString() {
			return "@PUT";
		}
	},

	DELETE {
		@Override
		public String toString() {
			return "@DELETE";
		}
	},

	PATCH {
		@Override
		public String toString() {
			return "@PATCH";
		}
	}

}
