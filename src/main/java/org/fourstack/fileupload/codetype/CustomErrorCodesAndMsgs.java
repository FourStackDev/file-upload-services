package org.fourstack.fileupload.codetype;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CustomErrorCodesAndMsgs {
	@JsonProperty("FILEUPLOAD_404")
	RESOURCE_NOT_FOUND_CODE {
		@Override
		public String toString() {
			return "FILEUPLOAD_404";
		}
	},
	
	@JsonProperty("Unable to found Requested Resource")
	RESOURCE_NOT_FOUND_MSG {
		@Override
		public String toString() {
			return "Unable to found Requested Resource";
		}
	},
	
	@JsonProperty("FILEUPLOAD_406")
	INVALID_FILE_NAME_CODE {
		@Override
		public String toString() {
			return "FILEUPLOAD_406";
		}
	},
	
	@JsonProperty("File name contains Invalid Characters. Please check.")
	INVALID_FILE_NAME_MSG {
		@Override
		public String toString() {
			return "File name contains Invalid Characters. Please check.";
		}
	},
	
	@JsonProperty("FILEUPLOAD_409")
	UNIQUE_CONSTRAINT_CODE {
		@Override
		public String toString() {
			return "FILEUPLOAD_409";
		}
	},
	
	@JsonProperty("Unique Constraint Violation occurred. Please check.")
	UNIQUE_CONSTRAINT_MSG {
		@Override
		public String toString() {
			return "Unique Constraint Violation occurred. Please check.";
		}
	}
}
