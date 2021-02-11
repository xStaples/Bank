package com.revature.model;

public class Transfer {
    private String senderUsername;
    private String receiverUsername;
    private double transferAmount;

    public Transfer() {
    }

    public Transfer(String senderUsername, String receiverUsername, double transferAmount) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.transferAmount = transferAmount;
    }

    @Override
    public String toString() {
        return "Transfer [receiverUsername=" + receiverUsername + ", senderUsername=" + senderUsername
                + ", transferAmount=" + transferAmount + "]";
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((receiverUsername == null) ? 0 : receiverUsername.hashCode());
        result = prime * result + ((senderUsername == null) ? 0 : senderUsername.hashCode());
        long temp;
        temp = Double.doubleToLongBits(transferAmount);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transfer other = (Transfer) obj;
        if (receiverUsername == null) {
            if (other.receiverUsername != null)
                return false;
        } else if (!receiverUsername.equals(other.receiverUsername))
            return false;
        if (senderUsername == null) {
            if (other.senderUsername != null)
                return false;
        } else if (!senderUsername.equals(other.senderUsername))
            return false;
        if (Double.doubleToLongBits(transferAmount) != Double.doubleToLongBits(other.transferAmount))
            return false;
        return true;
    }

}
