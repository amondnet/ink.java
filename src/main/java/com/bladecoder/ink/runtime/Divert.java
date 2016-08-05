package com.bladecoder.ink.runtime;

public class Divert extends RTObject {
	private int externalArgs;

	private boolean isConditional;

	private boolean isExternal;

	private boolean pushesToStack;

	private String variableDivertName;

	private RTObject targetContent;

	private Path targetPath;

	public PushPopType stackPushType = PushPopType.Tunnel;

	public Divert() {
		setPushesToStack(false);
	}

	public Divert(PushPopType stackPushType) {
		setPushesToStack(true);
		this.stackPushType = stackPushType;
	}

	public boolean equals(RTObject obj) {
		try {
			Divert otherDivert = obj instanceof Divert ? (Divert) obj : (Divert) null;
			if (otherDivert != null) {
				if (this.hasVariableTarget() == otherDivert.hasVariableTarget()) {
					if (this.hasVariableTarget()) {
						return this.getVariableDivertName().equals(otherDivert.getVariableDivertName());
					} else {
						return this.getTargetPath().equals(otherDivert.getTargetPath());
					}
				}

			}

			return false;
		} catch (RuntimeException __dummyCatchVar0) {
			throw __dummyCatchVar0;
		} catch (Exception __dummyCatchVar0) {
			throw new RuntimeException(__dummyCatchVar0);
		}

	}

	public int getexternalArgs() {
		return externalArgs;
	}

	public boolean hasVariableTarget() {
		return getVariableDivertName() != null;
	}

	public boolean isConditional() {
		return isConditional;
	}

	public boolean isExternal() {
		return isExternal;
	}
	public boolean getpushesToStack() {
		return pushesToStack;
	}

	public RTObject getTargetContent() throws Exception {
		if (targetContent == null) {
			targetContent = resolvePath(targetPath);
		}

		return targetContent;
	}

	public Path getTargetPath() throws Exception {
		// Resolve any relative paths to global ones as we come across them
		if (targetPath != null && targetPath.isRelative()) {
			RTObject targetObj = getTargetContent();

			if (targetObj != null) {
				targetPath = targetObj.getPath();
			}

		}

		return targetPath;
	}

	public String getTargetPathString() throws Exception {
		if (getTargetPath() == null)
			return null;

		return compactPathString(getTargetPath());
	}

	public String getVariableDivertName() {
		return variableDivertName;
	}

	@Override
	public int hashCode() {
		try {
			if (hasVariableTarget()) {
				int variableTargetSalt = 12345;
				return getVariableDivertName().hashCode() + variableTargetSalt;
			} else {
				int pathTargetSalt = 54321;
				return getTargetPath().hashCode() + pathTargetSalt;
			}
		} catch (RuntimeException __dummyCatchVar1) {
			throw __dummyCatchVar1;
		} catch (Exception __dummyCatchVar1) {
			throw new RuntimeException(__dummyCatchVar1);
		}

	}

	public void setExternalArgs(int value) {
		externalArgs = value;
	}

	public void setConditional(boolean value) {
		isConditional = value;
	}

	public void setExternal(boolean value) {
		isExternal = value;
	}

	public void setPushesToStack(boolean value) {
		pushesToStack = value;
	}

	public void setTargetPath(Path value) {
		targetPath = value;
		targetContent = null;
	}

	public void setTargetPathString(String value) {
		if (value == null) {
			setTargetPath(null);
		} else {
			setTargetPath(new Path(value));
		}
	}

	public void setVariableDivertName(String value) {
		variableDivertName = value;
	}

	@Override
	public String toString() {
		try {
			if (hasVariableTarget()) {
				return "Divert(variable: " + getVariableDivertName() + ")";
			} else if (getTargetPath() == null) {
				return "Divert(null)";
			} else {
				StringBuilder sb = new StringBuilder();
				String targetStr = getTargetPath().toString();
				Integer targetLineNum = debugLineNumberOfPath(getTargetPath());
				if (targetLineNum != null) {
					targetStr = "line " + targetLineNum;
				}

				sb.append("Divert");
				if (getpushesToStack()) {
					if (stackPushType == PushPopType.Function) {
						sb.append(" function");
					} else {
						sb.append(" tunnel");
					}
				}

				sb.append(" (");
				sb.append(targetStr);
				sb.append(")");
				return sb.toString();
			}
		} catch (RuntimeException __dummyCatchVar2) {
			throw __dummyCatchVar2;
		} catch (Exception __dummyCatchVar2) {
			throw new RuntimeException(__dummyCatchVar2);
		}

	}

}